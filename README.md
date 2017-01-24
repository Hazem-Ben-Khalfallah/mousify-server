# Mousify _server_
This app enables a user to control his PC mouse via an android phone. This repository contains **server side** (PC) source code.

Mobile side source code can be found in this [link](https://github.com/Hazem-Ben-Khalfallah/mousify-mobile)

## Installation
**Mousify _server_** installation is easy and can be done in five quick steps: 

1 - Download latest release from [**releases**](./releases) directory

2 - Make sure you have **java 1.8** installed in your environment. If not, follow this [link](https://java.com/en/download/help/download_options.xml) to install it. 

3 - Open your console where you have downloaded the jar and run 

    java -jar mousify-[SOME-VERSION].jar

This will start your server and make him listen to incoming requests.

4 - Open **Mousify** app in your Android phone and click on **Discover host** button or insert manually your PC IP address.

5 - If connection has been successful, you will be able to control your PC mouse from your phone.

## Change Logs
**0.1.0-snapshot**
- handle mouse right and left clicks
- handle text selection / double click
- handle wheel scroll

## Special Thanks
- **@EsotericSoftware** for **kryonet** TCP/UDP client/server library