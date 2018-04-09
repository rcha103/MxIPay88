# MxIPay88

| Name 	| Description 	|
|--------------	|-----------------------	|
| Author 	| Rionald Chancellor 	|
| Company 	| Magnus 	|
| Version 	| 1.0 	|
| Package name 	| PaymentInterfaces.mpk 	|
| Released 	| 5-5-2018 	|

### Description
MxIPay88 is a module that enables your Mendix application to use iPay88, a leading payment gateway within ASEAN region. Accept online payments such as MYR (Malaysia Ringgit) payment, as well as multi-currency payment processing on your Mendix application.

### Credits

MxIPay88 is an extension based from several other projects. Credit to the following:
  - [Erwin 't Hoen](https://github.com/Erwin-t-Hoen) for his work on [Open-Authentication-Module](https://github.com/Erwin-t-Hoen/Open-Authentication-Module). The payment callback request handler is based from his work.
  - [Mendix](https://github.com/mendix) for their work on [GeolocationForPhoneGap](https://github.com/mendix/GeolocationForPhoneGap). The widget used in this module is based on their widget implementation.
  - [Tjwoon](https://github.com/tjwoon) for his work on [csIPay88](https://github.com/tjwoon/csIPay88). The widget used in this module uses his plugin, which uses the iPay88 mobile SDK for Android and iOS.


### Usage Scenario

If your Mendix app requires a payment gateway to perform a transaction, MxIpay88 module will allow you to integrate your Mendix app with iPay88. This is especially true if your Mendix app is consumer oriented and if the consumers need to make payments for business activities.

### Features and limitations
- Implement your own logic for payment transaction of users even add user provisioning with a simple microflow
- Supports both front and backend request handler. Backend request handler is required by iPay88 in case successful payment does not redirect back to Mendix app and completes the transaction.
- Hybrid mobile app supported with custom widget provided


### Requirements
- Mendix 6 or later (tested on Mendix 7.5)
- Ipay88 merchant account
- If you want to support mobile payments then you must also have a mobile SDK account (contact iPay88 for more details)


### Installation and configuration
1. Import the module to your project
2. Download be_response.html and copy it to your theme folder
3. On your phonegap **config.xml** file, add the following line:
    ```sh
    <plugin name="csIPay88" source="git" spec="https://github.com/tjwoon/csIPay88" />
    ```
4. Connect the microflow **SUB_CreateIPay88Transaction** to your logic before prompting users to make payment
5. Use the snippet **SNP_ResponsivePaymentButton** to redirect the users to payment gateway
6. For mobile app, use **SNP_iPay88Widget** to redirect the users to mobile payment gateway
7. On Mendix startup microflow, add the Java actions **PaymentCallback_StartRequestHandler** and **PaymentCallbackBE_StartRequestHandler**. 
8. For **PaymentCallback_StartRequestHandler**, set the value as: ***pcallback/***
9. For **PaymentCallbackBE_StartRequestHandler**, set the value as: ***becallback/***
10. Make a copy of the microflow **ProcessPaymentResponse** and implement how you would like to process the payment once it is approved.

### Dependencies
1. Mendix 6 environment or higher
2. Mx Model Reflection module
3. Community Commons module
4. apache-httpcomponents-httpclient.jar
5. httpclient-4.3.5.jar
6. json-simple.jar
7. org.apache.httpcomponents.httpclient_4.3.5.jar
8. Ipay88 plugin (required for mobile payment)


### Known bugs
- On Android, the widget will always have issues executing the success and fail microflow. This is due to the plugin missing the onSaveInstanceState() implementation. As a result, a static failure message will appear for Android platform. Payment success will restart the mobile app, but once the backend payment callback is executed by iPay88, your update should be reflected. This issue does not happen in iOS. For more details, check [this issue](https://github.com/tjwoon/csIPay88/issues/13) 
### FAQ
- You can raise an issue on GitHub, I might be able to answer it if I have the time. Asking on Mendix forum probably won't get you the answer since I don't spend much time there.
- For questions specific to iPay88 please ask their support line. I do not work for iPay88, I simply make an implementation of it on Mendix.
- For questions specific to the PhoneGap plugin, you may ask directly to the [maintainer](https://github.com/tjwoon).
