
                        var d = (new Date()).getTime();
                        var wf = (wf==undefined)?{}:wf;
                        var wfs = (wfs==undefined)?{}:wfs;
                        var wfso = (wfso==undefined)?{}:wfso;
                        var wid = '8085801';
                        var euid = 'ml0B';

                        var wf_no_rep = (wfso[wid]==undefined)?true:false;


                        wfso.items = (wfso.items==undefined)?[]:wfso.items;
                        wfso.items[wfso.items.length] = wid;
                        wfso[wid] = (wfso[wid]==undefined)?{}:wfso[wid];
                        wfso[wid].id = '8085801';
                        wfso[wid].effect = 'fade';
                        wfso[wid].type = 'inline';
                        wfso[wid].position = 'center';
                        wfso[wid].animate = 'top';
                        wfso[wid].delay = '1';
                        wfso[wid].period = '1';
                        wfso[wid].css = unescape('<style type="text/css">#WFItem html{color:#000;background:#FFF;} #WFItem body, #WFItem div, #WFItem dl, #WFItem dt, #WFItem dd, #WFItem ul, #WFItem ol, #WFItem li, #WFItem h1, #WFItem h2, #WFItem h3, #WFItem h4, #WFItem h5, #WFItem h6, #WFItem pre, #WFItem code, #WFItem form, #WFItem fieldset, #WFItem legend, #WFItem input, #WFItem textarea, #WFItem p, #WFItem blockquote, #WFItem th, #WFItem td{margin-left:0;margin-right:0;margin-top:0;margin-bottom:0;padding-left:0;padding-bottom:0;padding-right:0;padding-top:0;width:auto;background-image:none; } #WFItem div, #WFItem dl, #WFItem dt, #WFItem dd, #WFItem ul, #WFItem ol, #WFItem li, #WFItem h1, #WFItem h2{border:none;background:none;float:none;width:auto;-moz-box-shadow:0;-webkit-box-shadow:0;box-shadow:0;text-indent:inherit;position:static;visibility:inherit; box-sizing:content-box; -webkit-box-sizing:content-box; -moz-box-sizing:content-box; line-height:normal;} #WFItem table{border-collapse:collapse;border-spacing:0;} #WFItem fieldset, #WFItem img{border:0;} #WFItem address, #WFItem caption, #WFItem cite, #WFItem code, #WFItem dfn, #WFItem em, #WFItem th, #WFItem var{font-style:normal;font-weight:normal;} #WFItem strong{font-style:normal;font-weight:bold;} #WFItem em{font-style:italic;font-weight:normal;position:static;}  #WFItem li{list-style:none;}caption, #WFItem th{text-align:left;} #WFItem h1, #WFItem h2, #WFItem h3, #WFItem h4, #WFItem h5, #WFItem h6{font-size:100%;font-weight:normal;} #WFItem q:before, #WFItem q:after{content: " ";} #WFItem abbr, #WFItem acronym{border:0;font-variant:normal;} #WFItem sup{vertical-align:text-top;} #WFItem sub{vertical-align:text-bottom;} #WFItem input, #WFItem textarea, #WFItem select{font-family:inherit;font-size:inherit;font-weight:inherit;height:auto;line-height:auto;} #WFItem input, #WFItem textarea, #WFItem select{*font-size:100%;} #WFItem legend{color:#000;} #WFItem .clearfix:after { visibility: hidden; display: block; font-size: 0; content: " "; clear: both; height: 0; } #WFItem .clearer { display: block; clear:both; font-size:0.1px; height:0.1px; line-height:0.1px; width:100%; } #WFWrapper .close { background:url(http://app.getresponse.com/images/common/highslide/close.png) repeat scroll 0 0 transparent; cursor:pointer; height:30px; position:absolute; right:-15px; top:-15px; width:30px;z-index:1; } #wfCloseX { background:url(http://app.getresponse.com/images/common/highslide/close.png) repeat scroll 0 0 transparent; cursor:pointer; height:30px; position:absolute; right:0; top:0; width:30px;z-index:1; cursor:pointer; } #WFItem .clrB {clear:both;text-align:left;} #WFItem * html .clearfix { zoom: 1; } #WFItem *:first-child+html .clearfix { zoom: 1; } #WFItem .wf-contbox { width:100%; } .wf-formTpl li:before {content: " ";} #WFItem form { display:inline-block; width:100%; } #WFItem label { margin:0; } #WFItem p { line-height:18px; font-size:11px; } #WFItem input[type="submit"] { cursor:pointer; text-shadow:none; filter:none; float:none; text-transform:none; letter-spacing:0; white-space: nowrap; word-wrap:normal; } #WFItem .wf-label { word-break: normal; width: auto; float: none; width: auto; } #WFItem {width:230px;}           #WFItem  { color: #555555; font-family: Arial,Helvetica,sans-serif; font-size: 12px; }  #WFItem  .wf-body li { padding: 10px 15px; clear: both; }  #WFItem  .actTinyMceElBodyContent ul li { padding: 3px; list-style-type: disc; }  #WFItem  .actTinyMceElBodyContent ol li { padding: 3px; list-style-type: decimal; }  #WFItem  .actTinyMceElBodyContent ul { margin-left: 20px; }  #WFItem  .actTinyMceElBodyContent ol { margin-left: 25px; }  #WFItem  .actTinyMceElBodyContent ol ol { margin-top: 0; margin-bottom: 0; }  #WFItem .wf-body { background-color:rgb(4, 102, 122);background-image: none;background-position: left top;background-repeat: no-repeat;border-color: #92757d;border-width: 0px;border-style: none;-webkit-border-top-left-radius: 0px;-moz-border-radius-topleft: 0px;border-top-left-radius: 0px;-webkit-border-top-right-radius: 0px;-moz-border-radius-topright: 0px;border-top-right-radius: 0px;-webkit-border-bottom-left-radius: 0px;-moz-border-radius-bottomleft: 0px;border-bottom-left-radius: 0px;-webkit-border-bottom-right-radius: 0px;-moz-border-radius-bottomright: 0px;border-bottom-right-radius: 0px;}  #WFItem  .wf-header { color: #2B93D3; background-color: #F5F5F5; background-image: none; background-position: left top; background-repeat: no-repeat; border-color: #6b6262; border-width: 0px; border-style: none; padding-left: 10px; padding-right: 10px; padding-top: 20px; padding-bottom: 0px; display: block; min-height: 32px; -webkit-border-top-left-radius: 0px; -moz-border-radius-topleft: 0px; border-top-left-radius: 0px; -webkit-border-top-right-radius: 0px; -moz-border-radius-topright: 0px; border-top-right-radius: 0px; -webkit-border-bottom-left-radius: 0px; -moz-border-radius-bottomleft: 0px; border-bottom-left-radius: 0px; -webkit-border-bottom-right-radius: 0px; -moz-border-radius-bottomright: 0px; border-bottom-right-radius: 0px; }  #WFItem  .wf-footer { color: #2B93D3; background-color: #F0F0F0; background-image: none; background-position: left top; background-repeat: no-repeat; border-color: #6b6262; border-width: 0px; border-style: none; padding-left: 10px; padding-right: 10px; padding-top: 10px; padding-bottom: 10px; display: block; min-height: 25px; -webkit-border-top-left-radius: 0px; -moz-border-radius-topleft: 0px; border-top-left-radius: 0px; -webkit-border-top-right-radius: 0px; -moz-border-radius-topright: 0px; border-top-right-radius: 0px; -webkit-border-bottom-left-radius: 0px; -moz-border-radius-bottomleft: 0px; border-bottom-left-radius: 0px; -webkit-border-bottom-right-radius: 0px; -moz-border-radius-bottomright: 0px; border-bottom-right-radius: 0px; }  #WFItem  .wf-input { padding: 2px 0; font-family: Arial; font-size: 14px; color: #000000; font-weight: normal; font-style: normal; text-decoration: none; background-color: #FFFFFF; border-width: 1px; border-color: #D9D9D9; border-style: solid; width: 100%; -webkit-border-top-left-radius: 8px; -moz-border-radius-topleft: 8px; border-top-left-radius: 8px; -webkit-border-top-right-radius: 8px; -moz-border-radius-topright: 8px; border-top-right-radius: 8px; -webkit-border-bottom-left-radius: 8px; -moz-border-radius-bottomleft: 8px; border-bottom-left-radius: 8px; -webkit-border-bottom-right-radius: 8px; -moz-border-radius-bottomright: 8px; border-bottom-right-radius: 8px; }  #WFItem  .wf-input { font-family: Arial; font-size: 14px; color: #555555; font-weight: normal; font-style: normal; text-decoration: none; background-color: #FFFFFF; border-width: 1px; border-color: #D9D9D9; border-style: solid; width: 100%; -webkit-border-top-left-radius: 8px; -moz-border-radius-topleft: 8px; border-top-left-radius: 8px; -webkit-border-top-right-radius: 8px; -moz-border-radius-topright: 8px; border-top-right-radius: 8px; -webkit-border-bottom-left-radius: 8px; -moz-border-radius-bottomleft: 8px; border-bottom-left-radius: 8px; -webkit-border-bottom-right-radius: 8px; -moz-border-radius-bottomright: 8px; border-bottom-right-radius: 8px; }  #WFItem .wf-label { font-family: arial;font-size: 12px;color:rgb(255, 172, 18);font-weight:bold;font-style: normal;text-decoration: none;padding-top: 3px;padding-bottom: 3px;padding-right: 3px;display: block;}  #WFItem  .wf-button { font-family: Arial; font-size: 22px; color: #FFFFFF; font-weight: bold; font-style: normal; text-decoration: none; background-color: #0DBAFF; background-image: url(http://app.getresponse.com/images/core/webforms/gradient_top.png); border-width: 0px; border-color: #000000; border-style: none; padding-left: 16px; padding-right: 16px; padding-top: 8px; padding-bottom: 8px; -webkit-border-top-left-radius: 8px; -moz-border-radius-topleft: 8px; border-top-left-radius: 8px; -webkit-border-top-right-radius: 8px; -moz-border-radius-topright: 8px; border-top-right-radius: 8px; -webkit-border-bottom-left-radius: 8px; -moz-border-radius-bottomleft: 8px; border-bottom-left-radius: 8px; -webkit-border-bottom-right-radius: 8px; -moz-border-radius-bottomright: 8px; border-bottom-right-radius: 8px; }  #WFItem  .wf-button:hover { }  #WFItem  .wf-submit { text-align: center; }  #WFItem  .wf-labelpos { float: none; width: auto; text-align: left; }  #WFItem  .wf-inputpos { float: none; width: auto; }  #WFItem  .wf-privacy { font-family: Arial; font-size: 12px; color: #B8B8B8; font-weight: normal; font-style: italic; text-decoration: none; text-align: center; border: none; }  #WFItem  .wf-privacyico { padding: 4px 0 0 20px; background: url(http://app.getresponse.com/images/core/webforms/lock.png) no-repeat left center; }  #WFItem  .wf-counter { font-family: Arial; font-size: 12px; color: #44454f; font-weight: bold; font-style: normal; text-decoration: none; text-align: center; }  #WFItem  .wf-poweredby { font-family: Arial; font-size: 10px; color: #B8B8B8; font-weight: normal; font-style: italic; text-decoration: none; text-align: center; }  #WFItem  .wf-link { font-family: Arial; font-size: 12px; color: #ffffff; font-weight: normal; font-style: normal; text-decoration: none; }  #WFItem  .wf-link:hover { font-family: Arial; font-size: 12px; color: #ffffff; font-weight: normal; font-style: normal; text-decoration: none; }  #WFItem  .wf-text { font-family: Arial; font-size: 12px; color: #B8B8B8; }  #WFItem  .wf-divider { border-top-width: 1px; border-color: #ffffff; border-style: solid; }  #WFItem  .wf-image { text-align: center; }  #WFItem  .wf-privacylink { color: #B8B8B8; font-style: italic; }  #WFItem  .wf-poweredbylink { color: #B8B8B8; font-style: italic; }  #WFItem  .wf-imgbox { overflow: hidden; }  #WFItem  .wf-rc-reload { display: inline-block; width: 16px; height: 16px; overflow: hidden; text-indent: -99999px; position: relative; top: 3px; margin-left: 3px; background: url(http://app.getresponse.com/images/core/webforms/captchaico.png) no-repeat 0 -32px; }  #WFItem  .wf-rc-audio { display: inline-block; width: 16px; height: 16px; overflow: hidden; text-indent: -99999px; position: relative; top: 3px; margin-left: 3px; background: url(http://app.getresponse.com/images/core/webforms/captchaico.png) no-repeat 0 0; }  #WFItem  .wf-rc-image { display: inline-block; width: 16px; height: 16px; overflow: hidden; text-indent: -99999px; position: relative; top: 3px; margin-left: 3px; background: url(http://app.getresponse.com/images/core/webforms/captchaico.png) no-repeat 0 -16px; }  #WFItem  #recaptcha_image { width: 200px; height: 38px; }  #WFItem  #recaptcha_image img { width: 200px; height: 38px; }  #WFItem  .wf-rc-boxm { width: 200px; margin: 0 auto; overflow: hidden; }  #WFItem  em { color: inherit; font-style: italic; }  #WFItem  .recaptcha_only_if_incorrect_sol { display:none; }         </style>').replace(/;/g,' !important;').replace(/}/g,' !important;}').replace(/!important;[\s]+!important;[\s]*}/g,' !important;}').replace(/WFItem/g,'WFItem'+wid);
                        wfso[wid].html = unescape('<div id="WFItem" class="wf-formTpl"><form accept-charset="utf-8" action="http://app.getresponse.com/add_contact_webform.html?u=ml0B" method="post"> <div class="wf-box"><div id="WFIheader" class="wf-header el" style="height: 67px; display: block;"><div class="actTinyMceElBodyContent"><p style="text-align: center ! important;"><strong><span style="font-size: 24px ! important; color: rgb(255, 0, 0) ! important; font-family: comic sans ms ! important; line-height: 14px ! important; display: inline-block ! important;">Free Java <br /></span></strong></p> <p style="text-align: center ! important;"><span style="font-size: 24px ! important;"><strong><span style="color: rgb(255, 0, 0) ! important; font-family: comic sans ms ! important;">Tutorial Videos</span></strong></span></p> <p style="text-align: center ! important;"><span style="font-size: 24px ! important;"><span style="font-size: 14px ! important;"><strong><span style="color: rgb(255, 0, 0) ! important; font-family: comic sans ms ! important;">(9,932+ guys benefited)</span></strong></span><br /></span></p></div><em class="clearfix clearer"></em></div><div id="WFIcenter" class="wf-body"><ul class="wf-sortable" id="wf-sort-id"> <li class="wf-name" rel="undefined" style="display: block;"><div class="wf-contbox"><div class="wf-labelpos"><label class="wf-label">Name:</label></div><div class="wf-inputpos"><input class="wf-input" name="name" type="text"></input></div><em class="clearfix clearer"></em></div></li><li class="wf-email" rel="undefined" style="display: block;"><div class="wf-contbox"><div class="wf-labelpos"><label class="wf-label">Email:</label></div><div class="wf-inputpos"><input class="wf-input wf-req wf-valid__email" name="email" type="text"></input></div><em class="clearfix clearer"></em></div></li><li class="wf-submit" rel="undefined" style="display: block;"><div class="wf-contbox"><div class="wf-inputpos"><input class="wf-button" name="submit" value="Send Me" style="width: 124px ! important; display: inline;" type="submit"></input></div><em class="clearfix clearer"></em></div></li><li class="wf-counter" rel="temporary" style="display: none;"><div class="wf-contbox"><div><span style="padding: 4px 6px 8px 24px; background: transparent url(http://app.getresponse.com/images/core/webforms/countertemplates.png) no-repeat scroll left 0px;" class="wf-counterbox"><span class="wf-counterboxbg" style="padding: 4px 12px 8px 5px; background: transparent url(http://app.getresponse.com/images/core/webforms/countertemplates.png) no-repeat scroll right -36px;"><span class="wf-counterbox0" style="padding: 5px 0px;">subscribed:</span><span style="padding: 5px;" name="http://app.getresponse.com/display_subscribers_count.js?campaign_name=codejavanet&var=0" class="wf-counterbox1 wf-counterq">184</span><span style="padding: 5px 0px;" class="wf-counterbox2"></span></span></span></div><em class="clearfix clearer"></em></div></li><li class="wf-captcha" rel="temporary" style="display: none;"><div style="display: block;" wf-captchaerror="Incorrect please try again" wf-captchasound="Enter the numbers you hear:" wf-captchaword="Enter the words above:" class="wf-contbox wf-captcha-1" id="wf-captcha-1"> </div><em class="clearfix clearer"></em></li><li class="wf-privacy" rel="undefined" style="display: block;"><div class="wf-contbox"><div><a target="_blank" class="wf-privacy wf-privacyico" href="http://www.getresponse.com/permission-seal?lang=en" style="height: 19px ! important; display: inline ! important;">We respect your privacy<em class="clearfix clearer"></em></a></div><em class="clearfix clearer"></em></div></li> </ul></div><div id="WFIfooter" class="wf-footer el" style="height: 25px; display: none;"><div class="actTinyMceElBodyContent"></div><em class="clearfix clearer"></em></div></div> <input type="hidden" name="webform_id" value="8085801"/></form></div>').replace(/::o::/g,'ó').replace(/::O::/g,'Ó').replace(/block;/g,' block !important;').replace(/none;/g,' none !important;').replace(/inline;/g,' inline !important;').replace(/WFItem/g,'WFItem'+wid);
                        wfso[wid].item = wfso[wid].css+wfso[wid].html;
                        wfso[wid].host = 'http://app.getresponse.com/';

                        wfso[wid].stay_url = 'yes';

                        wfs[wid] = (wfs[wid]==undefined)?{}:wfs[wid];

                        wfs[wid].is_captcha_enabled = 'no';
                        wfs[wid].language = 'en';
                        wfs[wid].default_email = '';

                        wf.t = (wf.t==undefined)?{}:wf.t;
                        wf.t[wid] = {};
                        wf.t[wid].validation_address_invalid = "Email address is invalid";
                        wf.t[wid].validation_required_field = "This is a required field ";
                        wf.t[wid].thank_you = "Thank You!";
                        wf.t[wid].translations_text_to_short = "Text too short";
                        wf.t[wid].translations_text_to_long = "Text too long";
                        wf.t[wid].translations_invalid_field_value = "Field value is invalid";
                        wf.t[wid].translations_date_field_default_mmddyyyy = "mm-dd-yyyy";
                        wf.t[wid].translations_date_field_default_month = "Month";
                        wf.t[wid].translations_date_field_default_day = "Day";
                        wf.t[wid].translations_date_field_default_year = "Year";

                        if (wf_no_rep) {
                            document.write(wfso[wid].item);
                        }


                        if ('undefined'== typeof onlyForFirsScript)
                        {
                            var onlyForFirsScript = true;

                            function addOnloadEvent(fnc){
                              if ( typeof window.addEventListener != 'undefined' )
                                window.addEventListener( 'load', fnc, false );
                              else if ( typeof window.attachEvent != 'undefined' ) {
                                window.attachEvent( 'onload', fnc );
                              }
                              else {
                                if ( window.onload != null ) {
                                  var oldOnload = window.onload;
                                  window.onload = function ( e ) {
                                    oldOnload( e );
                                    window[fnc]();
                                  };
                                }
                                else
                                  window.onload = fnc;
                              }
                            }
                            function in_array (needle, haystack) {
                                var key = '';
                                for (key in haystack) {
                                    if (haystack[key] === needle) {
                                        return true;
                                    }
                                }
                                return false;
                            }

                            addOnloadEvent(function()
                            {
                                if(wfs[wid].default_email)
                                {
                                    var inputs = document.getElementsByTagName('input');
                                    for (i = 0; i < inputs.length; i++)
                                    {
                                        if(inputs[i].getAttribute('name') == 'email')
                                        {
                                            inputs[i].value = wfs[wid].default_email;
                                        }
                                    }
                                }

                                var jsOut = document.createElement('script');
                                jsOut.setAttribute('type','text/javascript');
                                jsOut.setAttribute('src',wfso[wid].host+'javascripts/core/webforms/webform-out.js?'+d);

                                skrypty = document.getElementsByTagName('script');
                                if (typeof(temp) == 'undefined')
                                {
                                    var temp = new Array();
                                }

                                for (i=0;i<skrypty.length;i++)
                                {
                                    if (skrypty[i].getAttribute('src')!=null && skrypty[i].getAttribute('src').match(/view_webform\.js/) && in_array(skrypty[i].getAttribute('src'),temp) == false)
                                    {
                                        if (1 > document.getElementsByTagName('head').length)
                                        {
                                            var newHead = document.createElement('head');
                                            document.appendChild(newHead);
                                        }
                                        document.getElementsByTagName('head').item(0).appendChild(jsOut);
                                        temp[temp.length++] = skrypty[i].getAttribute('src');
                                    }
                                }
                            });
                         }
                    