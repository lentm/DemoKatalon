<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>html_Log in to Firco Continuityvar</name>
   <tag></tag>
   <elementGuidId>a5454c21-0706-491b-8585-46638ff06944</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>//html</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>html</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xmlns</name>
      <type>Main</type>
      <value>http://www.w3.org/1999/xhtml</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>
			
				
			
				

                
				
				Log in to Firco Continuity

				

				var iconLinkPath = '/continuity/en/themes/firco-continuity/images/favicon.ico';

				var iconRealPath = document.getElementById('icon-link').getAttribute('href');

				var pathTranslated = iconLinkPath != iconRealPath &amp;&amp; iconRealPath.indexOf(iconLinkPath) > 0;

				var junction = pathTranslated ? iconRealPath.substring(0, iconRealPath.indexOf(iconLinkPath)) : false;

				var pathCookieAlreadyChecked = false;

				var getRealCtxtPath = function(p) {

					if(typeof( p ) == 'undefined'){
						p = '';
					}

					var tmpCpath = '/continuity';

					if(junction){

						var rpath = junction + tmpCpath;

						if(!pathCookieAlreadyChecked){

							pathCookieAlreadyChecked = true; // check and operate only once per page

							var cookies = document.cookie.split(';');
	
							var pathCookieVal = null;
	
							for(var i=0; i&lt;cookies.length; i++){
	
								var name = cookies[i].substr(0, cookies[i].indexOf('=')).replace(/^\s+|\s+$/g, '');
	
								if(name=='prpath'){
	
									pathCookieVal = unescape(cookies[i].substr(cookies[i].indexOf('=')+1));
									break;
	
								}
							}
	
							if(document.cookie.indexOf('prpath') == -1
									|| rpath != pathCookieVal){
	
								document.cookie =
									'prpath=' + encodeURIComponent(rpath)
									+ '; path=' + rpath + ';';
	
								window.location.reload();
	
							}

						}

						return rpath + p;

					}

					return tmpCpath + p;
				};
				
				function getIEVersion()
				// Returns the version of Internet Explorer or a -1
				// (indicating the use of another browser).
				{
					return 11;
				}

				var Browser = {
					    IE: !!(window.attachEvent &amp;&amp; !window.opera),
					    Opera:  !!window.opera,
					    WebKit: navigator.userAgent.indexOf('AppleWebKit/') > -1,
					    Gecko:  navigator.userAgent.indexOf('Gecko') > -1 &amp;&amp; navigator.userAgent.indexOf('KHTML') == -1,
					    MobileSafari: !!navigator.userAgent.match(/Apple.*Mobile.*Safari/)
				};
				
				

				

				
							
		
								var applicationTheme = 'firco-continuity';

							
							
							
						

				

                
				
				

				
				
				
							
	
								var playbackEnabled = false;
									
								var captureEnabled = false;
							
							
						
							
							
						
				
				
				
				
					
								var applicationId = '2';
								
								var applicationVersion = '5.9.0.1.p1';
							
				


var pathToDwrServlet = getRealCtxtPath('/dwr');

				
				

				
				
				

				
	
	
	
	
		var pageNodeId = '62';
	
	


				
		
					var forceSelectedNav = null;
		
					var notificationsEnabled = false;
					
					var peepNotificationOnLoad = false;

					
					
					var advancedChartsEnabled = false;
					
					
					
					var singleColumnOperationsEnabled = false;
					
					
						
					var chartResizingEnabled = false;

					var currentUser = '';
		
					var currentUserId = '';
		
					var framebreakAttempts=0;var brokeout=(window.location.href.indexOf(&quot;breakout=true&quot;)!=-1);frameBreak();function frameBreak(){if(framebreakAttempts==3){Event.observe(window,&quot;load&quot;,function(){var c=document.createElement(&quot;div&quot;);c.id=&quot;small-framed-notice&quot;;var b=document.createElement(&quot;div&quot;);b.innerHTML=translate(&quot;page-framed-no-escape-warning&quot;);b.style.width=&quot;700px&quot;;c.appendChild(b);var a=$(document.getElementsByTagName(&quot;body&quot;)[0]);a.insert({top:c});$(&quot;main-area-content&quot;).remove()})}else{if(top!=self){framebreakAttempts++;top.location.replace(self.location.href+&quot;?breakout=true&quot;);setTimeout(&quot;frameBreak()&quot;,1000)}else{if(brokeout){Event.observe(window,&quot;load&quot;,function(){var d=document.createElement(&quot;div&quot;);d.id=&quot;small-framed-notice&quot;;var c=document.createElement(&quot;div&quot;);c.innerHTML=translate(&quot;page-framed-warning&quot;);d.appendChild(c);var b=$(document.getElementsByTagName(&quot;body&quot;)[0]);var a=$(&quot;main-area-content&quot;);a.style.marginTop=&quot;0px&quot;;b.insert({top:d})})}}}};
		
					
		
				
				
				
				
				
	
					top.processLaunching = false;
				
					function launch (processId) { 			

						if (top.processLaunching == false) {
						
							top.processLaunching = true;
							
							window.location.href = getRealCtxtPath('/'+currentLanguage+'/workflow/launch/') + processId + '/?windowId=' + window.name;
						}
					}

                    function evaluateLink (link) {

                        if (top.processLaunching == false) {

                            top.processLaunching = true;

                            window.location.href = eval(&quot;javascript:'&quot; + link + &quot;';&quot;);
                        }
                    }
					
								
				
				
				
			#katalon{font-family:monospace;font-size:13px;background-color:rgba(0,0,0,.7);position:fixed;top:0;left:0;right:0;display:block;z-index:999999999;line-height: normal} #katalon div{padding:0;margin:0;color:#fff;} #katalon kbd{display:inline-block;padding:3px 5px;font:13px Consolas,&quot;Liberation Mono&quot;,Menlo,Courier,monospace;line-height:10px;color:#555;vertical-align:middle;background-color:#fcfcfc;border:1px solid #ccc;border-bottom-color:#bbb;border-radius:3px;box-shadow:inset 0 -1px 0 #bbb;font-weight: bold} div#katalon-spy_elementInfoDiv {color: lightblue; padding: 0px 5px 5px} div#katalon-spy_instructionDiv {padding: 5px 5px 2.5px}
			
			
						

							
								
	
			
			
				top.processId = '0';
				top.processInstanceId = 'a7b8b598\x2D9692\x2D460e\x2Db596\x2D0215ab7672a3';
				top.tokenId = '6';


				serverDelay = null;
				sessionTimout = 7200;
				sessionTimout = (sessionTimout-serverDelay)*1000;
				
				top.skipOneUnloadEvent = false;

				Event.observe(window, 'load', function() {

					try {

						function browserUnloadNotification (evt) {
							
							if (top.skipOneUnloadEvent) {

								top.skipOneUnloadEvent = false;

								return;
							}
							
							if (captureEnabled &amp;&amp; captureRecordingInProgress) {
								
								saveInputRecording ();
							}							
							
							var sesReq = new Ajax.Request(contextPath + '/' + currentLanguage + '/overflow/browserWindowLoading/?windowId=' + window.name + '&amp;ieCache=4582922149050042050', {method: 'get', asynchronous: false});
						}

						Event.stopObserving(window, 'unload', browserUnloadNotification);
						
						Event.observe(window, 'unload', browserUnloadNotification);

					} catch (e) {
					}					
					
					

								if (window.name == null || window.name == '') {
									
									window.name = generateRandomNumber() + generateRandomNumber();
								}
							
								registerBrowserWindow ();
							
				});
				
			
		
					
   
      top.renderWidth = -1; top.renderHeight = -1; top.minRenderWidth = 1024; top.minRenderHeight = 768;
      Enter your authentication details to access the application.
      
         
      
      
         Username
         
         Password
         
         
         
         *
         
         
         *
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         var validationIds = new Array(), validationMessages = new Array(), validationOffsets = new Array(), validationMap = new Object();validationIds.push('text-input-element-16');validationMap['text-input-element-16'] = new Array();validationMessages['text-input-element-16'] = '(037) The Password field is required.';validationMap['text-input-element-16'].push(/\S/);validationIds.push('text-input-element-15');validationMap['text-input-element-15'] = new Array();validationMessages['text-input-element-15'] = '(037) The Username field is required.';validationMap['text-input-element-15'].push(/\S/);addJavascriptContent(function() { var script_element_14_data = {&quot;env&quot;:{&quot;S3KEY_AUTHENTICATION&quot;:&quot;false&quot;},&quot;translate&quot;:{&quot;3skey-no-token-found&quot;:&quot;&lt;a id=\&quot;3skey-loaded-link\&quot; style=\&quot;text-decoration:underline; color: #CC0000;\&quot; href=\&quot;javascript:sKeyApplet.rescan()\&quot;>Please insert your SWIFT Token and click here&lt;/a>&quot;},&quot;request&quot;:{}};
Login_Page_script_element_14(script_element_14_data);
 });new Tooltip($('text-input-element-15'), {});new Tooltip($('text-input-element-16'), {});
				

							
										
					
										
										
										
											
											
																    
								    

			

			
			
								

				
				
				Log in to Firco Continuity
				
														
			

(function($){
    $(document).ready(function(){
        $('ul.nav li a[data-toggle=&quot;tab&quot;]').each(function(e, el) {
            el.hide = function() {
                if (console &amp;&amp; console.log) {
                    console.log('### wont hide ###', this);
                }
            }.bind(el);
        });
    });
  })(jQuery);

			    

    
/html[1]</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>/html[1]</value>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//html</value>
   </webElementXpaths>
</WebElementEntity>
