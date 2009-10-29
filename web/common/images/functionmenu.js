//
// Function Menu
//
//
	var menuWaitGIF
	var menuGIF
    var myFunctionMenu
    var x
    var y
	var whereTemp
	var elementTemp

	var	menuWaitMsg = ""
	var functionMenuURL = ""
	var functionMenuPath = ""
	var sectionName = "" // additional string to distinguish different instances of the function menu for the same node.
	var lastFuncClickY = 0 // this stores Y coord of the last click on the function menu. We use it to fix IE's positioning of the menu.
	var lastFuncClickX = 0 // this stores X coord of the last click on the function menu. We use it to fix IE's positioning of the menu.
	
	
	function beforeMenuPop()
	{
		window.status = ""
	}

	function setSectionName( name )
	{
		sectionName = name;
	}

	function showFunctionMenu2( moreParam, id, e, theTarget, func )
	{
	    var myItem
		var funcMenu
		var innerFunctionMenu
		var funcMenuImg

		var i = 0


		if ( !pageLoaded )
	    {
	    	return
	    }

		if ( IE )
		{
			// Internet Explorer does not have the same method for cancelling event bubbling
			window.event.cancelBubble = true;
		}

		myItem = "x" + id + sectionName
		myItemContainerId = "z" + id + sectionName

		window.status = menuWaitMsg

		funcMenu = document.getElementById( myItemContainerId )
		funcMenuImg = document.getElementById( myItem )

		// check to see if the menu has already been inserted
		// into the div.
		if ( !funcMenu.firstChild )
		{
			retrieveData( id, moreParam, func )
		}

		lastFuncClickY = getY( funcMenuImg ) + 4
		lastFuncClickX = getX( funcMenuImg ) + 5

		// set this menu as the one that was clicked. This makes the
		// doMenu event handler (located in menu.js) show the menu.
		clickedMenu = myItemContainerId

		if ( IE )
		{
			window.setTimeout( "doMenu()", 200 )
		}

        // Reset in case an older function menu that doesn't want it is
        // clicked.
        sectionName = ""
	}

	function insertElementAfter( where, element )
	{
		var elementContainer = document.getElementById( where )

		if ( elementContainer )
		{
			if ( IE )
			{
				elementContainer.insertAdjacentHTML( 'BeforeEnd', element.outerHTML )
			}
			else
			{
				elementContainer.innerHTML = element.innerHTML
			}
		}
	}

	/* Triggers the iframe to retrieve new data */
	function retrieveData( id, moreParam, func )
	{
		var hiddenFrame = document.getElementById( "bridgeFrame" )
		var url = baseURL + "?func=ll&objID=" + id + "&objAction=functionsmenu2"
		var nextURL = window.location


		// Catch any access permission exceptions on parent check
		try {
			if ( window.parent.frames.length > 0 && window.parent.ButtonBarFrame == null )
    	    {
        	    nextURL = window.parent.location
        	}
		} catch ( e ) { }

		if ( typeof( func ) == "undefined" )
		{
			func = ""
		}

		/* Handle optional request handler override */
		if ( func != "" )
		{
			url = baseURL + func
		}

		if ( moreParam != "" )
		{
			url = url + "&" + moreParam
		}

		if ( "" != sectionName )
		{
			url = url + "&sectionName=" + sectionName
		}

		// Add timestamp to URL to prevent cache-use
		url += "&jsTicks=" + (new Date()).getTime()

		if ( -1 == url.toLowerCase().indexOf( '&nexturl=' ) )
		{
			url = url + "&nextURL=" + escape( window.location )
		}

		if ( hiddenFrame.src != url )
		{
			hiddenFrame.src = url
		}
	}

	//Written so that a click on a non-link menu item (like INFO) doesn't close the menu.
	function doNothing( e )
	{
		if ( IE )
		{
			// Internet Explorer does not have the same method for cancelling event bubbling
			window.event.cancelBubble = true;
		}
		else
		{
			// Use the DOM method
			e.stopPropagation()
		}
		
	}
	
	function doKeyboardSubMenu( subMenuID, parentID, event )
	{
		var subMenuItem = document.getElementById( subMenuID + '.0' )
		
		
		doNothing( event )
		doSubMenu( subMenuID, parentID )
		
		if ( lineBreaksAreNodes )
		{
			if ( subMenuItem.firstChild != null && subMenuItem.firstChild.nextSibling != null )
 			{
				subMenuItem.firstChild.nextSibling.focus()
			}
		}
		else
		{
			if ( subMenuItem != null )
 			{
				subMenuItem.firstChild.focus()
			}
		}
	}
	/////////////// OLD STYLE ... OBSOLETE //////////////
	
	
	if ( NS4 )
	{
		var appContainer
	}
	
	function getObjectAndXY( e )
	{
		if ( NS4 )
		{			
			if ( !appContainer )
			{
				appContainer = eval( 'document.appletContainer' )
			}

			//alert(e.pageX + " " + e.pageY + " appContainer.x=" + appContainer.x + " appContainer.y=" + appContainer.y )
    		if ( NS7 )
			{
				x = e.pageX - appContainer.x
				y = e.pageY - appContainer.y
			}
    		else if ( NS6 || useLayers )
			{
				x = e.pageX
				y = e.pageY
			}
			else
			{
				x = e.pageX - appContainer.x
				y = e.pageY - appContainer.y //window.pageYOffset
			}
		}
		else
		{
			window.divFM.style.top=document.body.scrollTop
			window.divFM.style.left=document.body.scrollLeft

			x = e.clientX
			y = e.clientY
		}

		if ( useLayers )
			myFunctionMenu = window.document.outerLayerNS.document.innerLayerNS.document.applets.FunctionMenu
		else
			myFunctionMenu = window.document.applets.FunctionMenu
			
		//window.status = y + " clientY=" + e.clientY  + " screenY=" + e.screenY + " scrollTop=" + document.body.scrollTop + " clientHeight=" + document.body.clientHeight + " menuTop=" + menuTop
	}
	function showFunctionMenu( moreParam, id, e, theTarget )
	{
	    var myItem

	    if ( !pageLoaded )
	    {
	    	return
	    }

		myItem = "x" + id + sectionName
		document.images[ myItem ].src = menuWaitGIF

		window.status = menuWaitMsg
		    
		getObjectAndXY( e )
		
		if ( typeof( theTarget ) == "undefined" )
			theTarget = ""

		if ( myFunctionMenu.showWithTarget( x, y, id, moreParam, theTarget ) == "" )
		{
		}
		document.images[ myItem ].src = menuGIF
		
		window.status = ""
	}
	
	// figure out what this exists for ...
	function showFunctionMenuAdv( theURL, id, e, theTarget )
	{
	    var myItem

	    if ( !pageLoaded )
	    {
	    	return
	    }

        id = "" + id
		myItem = "x" + id + sectionName
		document.images[ myItem ].src = menuWaitGIF

		window.status = menuWaitMsg
		    
		getObjectAndXY( e )

		if ( typeof( theTarget ) == "undefined" )
			theTarget = ""

		if ( myFunctionMenu.showAdv( x, y, id, theURL, theTarget ) == "" )
		{
		}
		
		document.images[ myItem ].src = menuGIF
		
		window.status = ""
	}

	function onLoadFunctionMenu( argMenuGif, argMenuWaitGif )
	{
	    var waitImg = new Image()
		
		pageLoaded = true
	    menuGIF = argMenuGif
	    menuWaitGIF = argMenuWaitGif
	    
	    waitImg.src = menuWaitGIF									
	}

    function popup_callback( theURL, theTarget )
    {
	    if ( !pageLoaded )
	    {
	    	return
	    }
		
		theURL = unescape( theURL )

		if ( __LeavePage == "" || ( eval( __LeavePage + "( '" + theURL + "', '" + theTarget + "')" ) ) )
		{
			if ( IE && ( theURL.toLowerCase().indexOf( 'objaction=download' ) > 0 ) )
  			{
				topFrame = getLLTop(self)
				
				if ( topFrame == self )
 					openURL( theURL, theTarget )
 				else
 					topFrame.location.href = theURL
   			}
  			else
 	        {
 	           	openURL( theURL, theTarget )
 	        }   
		}
	}

