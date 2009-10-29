//
// Dashboard
//
	var g_CheckDashboardFuncID = -1;
	var g_focusObject = null;
	var g_delayLoadTime = 500;

    var origWidth
    var origHeight
	
    function onLoadFunction()
    {
    	pageLoaded = true

    	if ( !( isMac && NS4 ) )
    	{
			if ( typeof( window.document.applets.Dashboard ) != "undefined" )
			{
        		onResizeFunction()
			}
		}

		executeAtlasFunction( 'AtlasAutoSync', document.location )
		saveAtlasURL()

		if ( g_focusObject != null )
		{
			// sanity check...
			if ( g_CheckDashboardFuncID != -1 )
			{
				clearInterval( g_CheckDashboardFuncID );
			}

 			g_CheckDashboardFuncID = setInterval( "checkDashboardFunc()", g_delayLoadTime);
		}

        return false
    }

	function checkDashboardFunc()
	{
		var appletsLoaded = true;
		for ( var i = 0; i < window.document.applets.length; i++ )
		{
			if ( typeof( window.document.applets[i] ) != "object" )
			{
				appletsLoaded = false;
			}
		}

		if ( appletsLoaded )
		{
			if ( g_focusObject != null )
			{
				if ( typeof( g_focusObject ) == "string" )
				{
					g_focusObject = eval( g_focusObject );
				}
					
				if ( typeof( g_focusObject ) == "object" )
				{
					g_focusObject.focus();
				}

				clearInterval( g_CheckDashboardFuncID );
			}
		}
	}
    
    function getAppletWidth()
    {
        var appSize

		// The applet width is relative in the beginning because before the page is layed
		// out the size is 0.
		// The proper size will be set through the onResize function
		appSize = Math.floor( window.document.body.clientWidth * ( 70 / 100 ) )
		
        return appSize
    }

