CAMPUS-FLYNEWS
=============
Client : Android
Server : PHP WordPress
Bridge : JSON 

What is it?
-------------
It is an APP designed for getting latest school news in Android Platform , administrator writes articles on Wordpress and 
the users will snyc the date from server, and users can commnet and share the article on Weibo or QZone .etc.

Technology Used
-------------
Client
‘’‘    
    //JSON , IMAGE Handler
    compile 'com.squareup.retrofit:retrofit:1.6.1'
    compile 'com.squareup.okhttp:okhttp-urlconnection:2.0.0'
    compile 'com.squareup.okhttp:okhttp:2.0.0'
    compile 'com.squareup.picasso:picasso:2.3.4'
    //compile 'com.android.support:support-v4:20.0.0'
    //compile project(':fadingactionbar')
    compile 'net.simonvt.menudrawer:menudrawer:3.0.6'
    compile project(':pullToRefresh')’‘’

Server: 
Wordpress JSON-API