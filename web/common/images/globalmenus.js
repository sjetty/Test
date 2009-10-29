function showGlobalMenu( menuName, showLocation )
{
    var globalMenu = document.getElementById( menuName )
    var showLoc = document.getElementById( showLocation )
    //alert(document.getElementById("a00"));
    globalMenu.style.top = getY( showLoc ) + showLoc.offsetHeight

	if ( globalMenu.offsetWidth > showLoc.offsetWidth )
	{
		globalMenu.style.left = getX( showLoc ) +  showLoc.offsetWidth - globalMenu.offsetWidth
	}
	else
	{
		globalMenu.style.left = getX( showLoc )
		globalMenu.style.width = showLoc.offsetWidth
	}
    //alert("in showGlobalMenu");
	clickedMenu = menuName
	
	// take over from doMenu
	document.onclick = unhighlightGlobalMenuTitle
	window.onresize = unhighlightGlobalMenuTitle
    //alert("end showGlobalMenu")
    return true;
}

function menu_callback( theURL, theTarget )    
{
    //alert('hi');
    if ( pageLoaded )
 	{
         //alert('pageLoaed');
        return
    }
    //alert('pageNotLoaded');
    if ( __LeavePage == "" || ( eval( __LeavePage + "( '" + theURL + "', '" + theTarget + "')" ) ) )
	{
			openURL( theURL, theTarget )
	}
    //alert('after menu_callback');
}

function unhighlightGlobalMenuTitle()
{
	var	menuWindow = getMenuWindow()
	var	menuShown = getMenuShown()


	if ( ( menuWindow == self.window ) && ( menuShown != clickedMenu ) && ( document.getElementById( menuShown + 'GlobalMenu' ) != null ) )
	{
		document.getElementById( menuShown + 'GlobalMenuImage' ).src = getArrowBlack()
		//document.getElementById( menuShown + 'GlobalMenu' ).firstChild.nextSibling.style.color = "#05447e"
	}
    //alert("in unhighlightGlobalMenutitle");
    doMenu()
}

function setArrowBlack( id )
{	
	 if ( document.getElementById( id ).style.visibility != '' )
	 {
		 if ( document.getElementById( id ).style.visibility == 'hidden' )
		 {
			//document.getElementById( id + 'GlobalMenu' ).firstChild.nextSibling.style.color = "#05447e"
			document.getElementById( id + 'GlobalMenuImage' ).src = getArrowBlack()
		}
	  }
	  else
	  {	
		 //document.getElementById( id + 'GlobalMenu' ).firstChild.nextSibling.style.color = "#05447e"
		 document.getElementById( id + 'GlobalMenuImage' ).src = getArrowBlack()
	  }
}	

function setArrowRed( id )
{
	//document.getElementById( id + 'GlobalMenu' ).firstChild.nextSibling.style.color = "#ff0000"
	document.getElementById( id + 'GlobalMenuImage' ).src = getArrowRed()
}
