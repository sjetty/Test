var intervalIndex
var scrollingItems

var lastTickerHeight = 0
var scrollTime = 100
var scrollPause = 2000
var index = 0
var tickerHeight = 15


function showTicker()
{
	var source
	var newsItem
	var newsItems
	var newsItemsToShow
	var goodChild
	
	var i = 0
	var j = 0
	var sourcesHeight = 0
	var numNewsItems = 0
	var newsTicker = document.getElementById( 'newsTicker' )
	var sources = newsTicker.childNodes


	newsTicker.onmouseover = pauseTicker
	newsTicker.onmouseout = unpauseTicker
	
	for ( i = 0; i < sources.length; i++ )
	{
		source = sources[ i ]
		
		if ( source.nodeType == 1 ) // 1 == Node.ELEMENT_NODE
		{
			if ( !goodChild )
			{
				goodChild = source
			}
			
			sourcesHeight = sourcesHeight + source.offsetHeight
			
			newsItems = source.childNodes
			for ( j = 0; j < newsItems.length; j++ )
			{
				newsItem = newsItems[ j ]
				
				if ( newsItem.nodeType == 1 ) // 1 == Node.ELEMENT_NODE
				{
					newsItem.style.height = newsTicker.style.height
				}
				else
				{
					source.removeChild( newsItem )
					j--
				}
			}
		}
	}
	
	// IE doesn't want to give me the height of the news ticker so we use the hard code method.
	if ( IE )
	{
		goodChild.style.top = tickerHeight
		goodChild.firstChild.style.top = -tickerHeight
	}
	else
	{
		goodChild.style.top = newsTicker.offsetHeight
		goodChild.firstChild.style.top = -newsTicker.offsetHeight
	}
	
	scrollingItems = goodChild.childNodes
	if ( IE )
		intervalIndex = newSetInterval( rollUp, scrollPause + ( tickerHeight * scrollTime ), goodChild.childNodes, tickerHeight )
    else
		intervalIndex = window.setInterval( rollUp, scrollPause + ( tickerHeight * scrollTime ), goodChild.childNodes, tickerHeight )
}

function rollUp( scroller, viewPort )
{
	for ( i = 1; i <= viewPort; i++ )
	{
		if ( index + 1 < scroller.length )
		{
			if ( IE )
				newSetTimeout( rollOne, (i * scrollTime), scroller[ index ], scroller[ index + 1 ], -i, viewPort )
			else
				window.setTimeout( rollOne, i * scrollTime, scroller[ index ], scroller[ index + 1 ], -i, viewPort )
		}
		else if ( index + 1 == scroller.length )
		{
			if ( IE )
				newSetTimeout( rollOne, (i * scrollTime), scroller[ index ], scroller[ 0 ], -i, viewPort )
			else
				window.setTimeout( rollOne, i * scrollTime, scroller[ index ], scroller[ 0 ], -i, viewPort )
		}
		else
		{
			if ( IE )
				newSetTimeout( rollOne, (i * scrollTime), scroller[ 0 ], scroller[ 1 ], -i, -viewPort )
			else
				window.setTimeout( rollOne, i * scrollTime, scroller[ 0 ], scroller[ 1 ], -i, viewPort )
		}
	}
	
	if ( index + 1 < scroller.length )
	{
		index = index + 1
	}
	else if ( index + 1 == scroller.length )
	{
		index = 0
	}
}

function rollOne( topNode, nextNode, newHeight, viewPort )
{
	topNode.style.top = ( newHeight - viewPort ) + "px"
	nextNode.style.top = newHeight  + "px"
}

function pauseTicker()
{
    if ( IE )
	    newClearInterval( intervalIndex )
    else
		window.clearInterval( intervalIndex )
}

function unpauseTicker()
{
    if ( IE )
	    intervalIndex = newSetInterval( rollUp, scrollPause + ( tickerHeight * scrollTime ), scrollingItems, tickerHeight )
	else
	    intervalIndex = window.setInterval( rollUp, scrollPause + ( tickerHeight * scrollTime ), scrollingItems, tickerHeight )
}
