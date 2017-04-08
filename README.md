# Mousify _server_
This app enables a user to control his PC mouse via an android phone. This repository contains **server side** (PC) source code.

Mobile side source code can be found in this [link](https://github.com/Hazem-Ben-Khalfallah/mousify-mobile)

## Requirements
Make sure you have **java 1.8** installed in your environment. If not, follow this [link](https://java.com/en/download/help/download_options.xml) to install it.
 
## Server installation
**Mousify _server_** installation is easy and can be done in five quick steps: 

### Windows & MacOs
1 - Download mousify-[SOME-VERSION].jar file latest release from [**releases**](./releases) directory.

2 - Open your console where you have downloaded the jar and run 

    java -jar mousify-[SOME-VERSION].jar

This will start your server and make him listen to incoming requests.

### Linux
1 - Download mousify-[SOME-VERSION].run executable file from [**releases**](./releases) directory.

2 - Grant this file execution permission

    chmod -x ./mousify-[SOME-VERSION].run

3 - Run server

    ./mousify-[SOME-VERSION].run

## Communication with Android app
1- Install **Mousify** app on your Android phone. You can find this app on **Amazon underground** store or on **Google Play!** store. 

2 - Open **Mousify** app in your Android phone and click on **Discover host** button or insert manually your PC IP address.

3 - If connection has been successful, you will be able to control your PC mouse from your phone.

## Change Logs
**1.0**
- generate Linux executable file

**0.1.0-snapshot**
- handle mouse right and left clicks
- handle text selection / double click
- handle wheel scroll

## Special Thanks
- **@EsotericSoftware** for **kryonet** TCP/UDP client/server library