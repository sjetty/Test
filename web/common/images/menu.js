var clickedMenu = ""
var subMenus = new Object()
var	pageLoaded = false

var divHideSelect = new Array()
var iFrameHideSelect = new Array()

document.onclick = doMenu
document.onmousedown = mouseDownHandler
window.onresize = hideMenu

// Return the highest container where we can store global info
function _getTopContainer()
{
	var		w = window

	// Stop going up when either there is nothing there, or we are already there,
	// or we don't have access permission go there.
	try {
	    while ( w.parent != null && w != w.parent && typeof( w.parent ) != "undefined" )
	    {
			w.parent._llAccessTest = true
 			w = w.parent
 		}
 	} catch ( e ) { }

	return w
}

// Store menuShown such that it will be accessible to all LL frames
function setMenuShown( ms )
{
	var	w = _getTopContainer()

	w._menuShown = ms
}

function getMenuShown( )
{
	var	w = _getTopContainer()
	var ms = ''

	if ( typeof( w._menuShown ) != "undefined" )
	{
        ms = w._menuShown
	}
    //else
    //{
    //    alert("menuShown undefined");
    //}

    return ms
}

// Store menuWindow such that it will be accessible to all LL frames
function setMenuWindow( mw )
{
	var	w = _getTopContainer()

	w._menuWindow = mw
}

function getMenuWindow( )
{
	var	w = _getTopContainer()
	var mw = null

	if ( typeof( w._menuWindow ) != "undefined" )
	{
		mw = w._menuWindow
	}

	return mw
}


function mouseDownHandler( e )
{
	var menuWindow = getMenuWindow()


	// If we have a menu displayed in a different window [frame], call
	// that window's onclick handler to tear down the menu. If it is in
	// the same window, let the real onclick event code determine whether
	// it is a click on the same menu or a different menu.

	if ( menuWindow != null && window != menuWindow )
	{
		menuWindow.document.onclick()
	}
}


function showMenu( menuName )
{
	clickedMenu = menuName
}

function hideMenu( )
{
	clickedMenu = ''
	doMenu()
}

function makeIgnorantFramesHideMenus()
{
	var i

	// Note: window.document is resolved when f is declared, NOT when executed.
	var f = new Function( "window.document.onclick()" )


	for (i = 0; i < window.frames.length; i++)
	{
		if ( typeof( window.frames[i].document.onmousedown ) != "function" )
		{
			// Set the handler which will advise this window of any clicks,
			// so that menus can be torn down
			window.frames[i].document.onmousedown = f
		}
	}
}


function doMenu()
{
	var myfunctionMenu
	var	insideFunctionMenuId
	var	insideFunctionMenu

	var menuShown = getMenuShown()
	var menuWindow = getMenuWindow()


	// User clicked on same or different menu - in either case,
	// tear it down if it is in our window. If it is in another window,
	// the mouseDownHandler already dealt with it - this also protects
	// us from an IE bug which calls child IFRAME onclick handlers repeatedly
	// when there is a click in the parent frame.
	if ( menuShown != "" && menuWindow == self.window )
	{
		if ( menuShown == clickedMenu )
		{
		    // Click on same menu - interpret as hide only
		    clickedMenu = ""
  		}

		// Hide the menu
		myFunctionMenu = document.getElementById( menuShown )
		tearDownMenu( myFunctionMenu )
	}

	// Click on a menu
	if ( clickedMenu != "" )
	{
		myFunctionMenu = document.getElementById( clickedMenu )

		// Test if is a function menu. Weird, but true.
		if ( myFunctionMenu.id.indexOf( "z" ) == 0 )
		{
			insideFunctionMenuId = 'functionMenu' + myFunctionMenu.id.substring(1)
			insideFunctionMenu =  document.getElementById( insideFunctionMenuId )

			if ( !insideFunctionMenu )
			{
			    window.status = "Waiting for data..."
			    window.setTimeout( "doMenu()", 100 )
				return
			}
   		}

		renderMenu( myFunctionMenu, insideFunctionMenu )
		clickedMenu = ""
		window.status = ""

        //alert("in doMenu")
        // In case child frames have reloaded, we always reset their onmousedown handler.
		// This is necessary for View as Web Page and Edit Permissions empty right frame,
		// which don't load menu.js.
		makeIgnorantFramesHideMenus()
        //alert("end doMenu")
    }

}

function renderMenu( menu, insideMenu )
{
	var	availHeight
	var menuWidth
	var menuHeight

	if ( insideMenu && insideMenu.offsetWidth && insideMenu.offsetHeight )
	{
		menuWidth = insideMenu.offsetWidth
		menuHeight = insideMenu.offsetHeight

		// Browser-dependent available height, with adjustment for scrollbar
		availHeight = ( IE ? document.body.offsetHeight : window.innerHeight )

		// Adjust x-position if menu would render beyond right edge of page
		if ( ( document.body.scrollLeft + document.body.offsetWidth - lastFuncClickX ) < menuWidth )
		{
			lastFuncClickX = lastFuncClickX - menuWidth
		}

		menu.style.left = lastFuncClickX

    	// Adjust y-position if menu would render below visible part of page
		if ( document.body.scrollTop + availHeight - lastFuncClickY < menuHeight )
		{
			lastFuncClickY = Math.max( 10, document.body.scrollTop + availHeight - ( menuHeight + 25 ) )
		}

		menu.style.top = lastFuncClickY

		showMenuUnderBelly( lastFuncClickX, lastFuncClickY, menuHeight - 10, menuWidth - 12 )
	}
	else
	{
		showMenuUnderBelly( getX( menu ), getY( menu ), menu.offsetHeight - 10 , menu.offsetWidth - 12 )
	}

	// Push the menu up to the top so that its sub menus will be above the iframe underbelly.
	menu.style.zIndex = 5

	menu.style.visibility = "visible"

    setMenuShown( menu.id )
	setMenuWindow( window )
}

function tearDownMenu( menu )
{
	var subMenuToHide

	menu.style.visibility = "hidden"

	hideMenuUnderBelly( true )

	if ( menu.firstChild )
	{
		// Hide the original menu's sub menus.
		if ( lineBreaksAreNodes )
		{
			subMenuToHide = menu.firstChild.nextSibling.id
		}
		else
		{
			subMenuToHide = menu.firstChild.firstChild.id
		}

		while ( ( subMenus[ subMenuToHide ] ) && ( subMenus[ subMenuToHide ] != "" ) )
		{
			menu = document.getElementById( subMenus[ subMenuToHide ] )
			menu.style.visibility = "hidden"
			
			hideMenuUnderBelly( true )
			
			subMenus[ subMenuToHide ] = subMenus[ menu.id ]

			if ( menu.id.lastIndexOf( "Sub" ) != -1 )
			{
				loLight( menu.id.replace( "Sub", "") );
			}
		}
	}
		
	subMenus = new Object()
	setMenuShown( "" )
	setMenuWindow( null )
}


/* 			SUB MENU 							*/

function doSubMenu( menuName, parentMenu )
{
	var parentMenuItem
	var oldMenu
	var	x
	var	y

	var subMenuToShow = document.getElementById( menuName + "Sub" )
	var	availHeight = ( IE ? document.body.offsetHeight : window.innerHeight ) - 20

	if ( subMenus[ parentMenu ] != menuName + "Sub" )
	{

		if ( subMenuToShow )
		{
			// If there is a submenu for this menu that is already shown, hide it. Unless the submenu that is shown
			// is also the one to be shown.
			if ( subMenus[ parentMenu ] && subMenus[ parentMenu ] != "" )
			{	
				while ( ( subMenus[ parentMenu ] ) && ( subMenus[ parentMenu ] != "" ) )
				{
					oldMenu = document.getElementById( subMenus[ parentMenu ] )
					oldMenu.style.visibility = "hidden"
					
					hideMenuUnderBelly( false )
					
					loLight( oldMenu.id.replace( "Sub", "") )
	
					subMenus[ parentMenu ] = subMenus[ oldMenu.id ]
					subMenus[ oldMenu.id ] = ""
				}
			}
			
			parentMenuItem = document.getElementById( menuName )
			
			// Magic value is bad. Done to fix IE's different positioning.
			if ( parentMenuItem.id.indexOf( "funcMenu" ) >= 0 )
			{
				x = getX( parentMenuItem )
				y = getY( parentMenuItem )

				if ( document.body.scrollLeft + document.body.offsetWidth < x + parentMenuItem.offsetWidth + subMenuToShow.offsetWidth )
				{
					x = x - subMenuToShow.offsetWidth - lastFuncClickX
				}
				else
				{
					x = x + parentMenuItem.offsetWidth + 1 - lastFuncClickX
				}
				
				if ( document.body.scrollTop + availHeight < y + subMenuToShow.offsetHeight )
				{
					y = y + parentMenuItem.offsetHeight - subMenuToShow.offsetHeight - lastFuncClickY
					
					//Check if the top of the submenu is oustide of the browser
					//Set the y so the top of the menu was inside of the browser window
					if ( y + lastFuncClickY < 0 )
                    {
                        y = 10 - lastFuncClickY
                    }
				}
				else
				{
					y = y + 4 - lastFuncClickY
				}
				
				subMenuToShow.style.left = x
				subMenuToShow.style.top = y
			}
			else
			{
				x = getX( parentMenuItem ) + parentMenuItem.offsetWidth
				y = getY( parentMenuItem )
				subMenuToShow.style.left = x
				subMenuToShow.style.top = y
			}
	
			subMenuToShow.style.visibility = "visible"
			subMenus[ parentMenu ] = menuName + "Sub"
			
			showMenuUnderBelly( x + lastFuncClickX, y + lastFuncClickY, subMenuToShow.offsetHeight - 10, subMenuToShow.offsetWidth - 12 )
		}
		else if ( subMenus[ parentMenu ] && subMenus[ parentMenu ] != "" )
		{
			hideMenuUnderBelly( false )
			oldMenu = document.getElementById( subMenus[ parentMenu ] )
			oldMenu.style.visibility = "hidden"
			
			loLight( oldMenu.id.replace( "Sub", "") )
			
			subMenus[ parentMenu ] = ""
		}
	}
}

function hiLight( menuName )
{
	var menu = document.getElementById( menuName )
	menu.className = 'menuItemHover'
}
function loLight( menuName )
{
	var menu = document.getElementById( menuName )
	menu.className = 'menuItem'
}

//function menu_callback( theURL, theTarget )
//{
//	if ( !pageLoaded )
//	{
//		return
//	}
//	alert('in menu_callback')
//	if ( __LeavePage == "" || ( eval( __LeavePage + "( '" + theURL + "', '" + theTarget + "')" ) ) )
//	{
//		openURL( theURL, theTarget )
//	}
//    alert('after menu_callback')
//}

function getX(obj)
{
  return( obj.offsetParent == null ? obj.offsetLeft : obj.offsetLeft + getX( obj.offsetParent ) );
}

function getY(obj)
{
  return( obj.offsetParent == null ? obj.offsetTop : obj.offsetTop + getY( obj.offsetParent ) );
}

function showMenuUnderBelly( x, y, height, width )
{
	var	size

	var	index = 0


	if (  divHideSelect.length == 0 )
	{
		if ( document.getElementById( 'HideSelectDiv' ) == null || document.getElementById( 'HideSelect' ) == null )
		{
			// Can't show underbelly yet... page not fully loaded
			return
 		}
		divHideSelect[ 0 ] = document.getElementById( 'HideSelectDiv' );
		iFrameHideSelect[ 0 ] = document.getElementById( 'HideSelect' );
	}
	else
	{
		divHideSelect[ divHideSelect.length ] = divHideSelect[ 0 ].cloneNode( true )
		iFrameHideSelect[ iFrameHideSelect.length ] = iFrameHideSelect[ 0 ].cloneNode( true )

		document.body.appendChild( divHideSelect[ divHideSelect.length - 1 ] )
		document.body.appendChild( iFrameHideSelect[ iFrameHideSelect.length - 1 ] )
	}

	size = divHideSelect.length - 1

	divHideSelect[ size ].firstChild.width =  width
	divHideSelect[ size ].firstChild.height =  height

	divHideSelect[ size ].style.top = y
	divHideSelect[ size ].style.left = x
	divHideSelect[ size ].style.display = "block";

	iFrameHideSelect[ size ].style.width =  width + 12;
	iFrameHideSelect[ size ].style.height = height + 10;

	iFrameHideSelect[ size ].style.top = divHideSelect[ size ].style.top;
	iFrameHideSelect[ size ].style.left = divHideSelect[ size ].style.left;
	iFrameHideSelect[ size ].style.zIndex = divHideSelect[ size ].style.zIndex - 1;
	iFrameHideSelect[ size ].style.display = "block";
}


function hideMenuUnderBelly( removeAll )
{
	var	index = 0

	if (  divHideSelect.length != 0 )
	{
		if ( removeAll )
		{
			while ( index < divHideSelect.length )
			{
				divHideSelect[ index ].style.display = "none";
				iFrameHideSelect[ index ].style.display = "none";
				index = index + 1
			}
			
			divHideSelect.length = 0
			iFrameHideSelect.length = 0
		}
		else
		{
			divHideSelect[ divHideSelect.length - 1 ].style.display = "none";
			iFrameHideSelect[ divHideSelect.length - 1 ].style.display = "none";
			divHideSelect.length = divHideSelect.length - 1
			iFrameHideSelect.length = iFrameHideSelect.length - 1
		}
	}
}
