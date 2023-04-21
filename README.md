# Mobile-Application-Penetration-Testing

The application was developed for penetration testing and Mitigation for the vulneribilites.

The Images and Description belove shows the OWASP Top 10 vulnaribilities and thier fixes:

1. Insecure Data Storage
Evidence:
The screenshot below shows the information submitted by the user is saved in shared preferences (medication name, dosage, and frequency), and this information is considered sensitive. Even though a user's prescription information is confidential, it is saved in shared preferences as plain text. Without ever launching the app, the attacker can access the data directly from the device.
![1](https://user-images.githubusercontent.com/53125151/233704436-35b5672a-9db2-489b-a387-b00d6b25c576.jpg)

Mitigation:
![2](https://user-images.githubusercontent.com/53125151/233704987-3d104a41-1add-4928-bf3b-b903c6bf1c45.jpg)
Implementing Firebase database with secure communication to a cloud storage offers the mitigation. Before creating the pharmaceutical data as an object and sending it to the database, Firebase Database requests user authentication. Figure 15 demonstrates that a database reference is being called since the application natively supports Firebase.

2. Insecure Authentication
Evidence:
The username and password are hardcoded in the code, which means that anybody with access to the app's code can readily see and know the credentials, making it easy for an attacker to obtain unauthorised access to the app. The password is kept in plain text in the code, making it easily accessible to an attacker or someone who decompiles the app.
![3](https://user-images.githubusercontent.com/53125151/233705062-ff9a3414-eca5-4aee-92c9-836a713364a1.jpg)

Mitigation:
![4](https://user-images.githubusercontent.com/53125151/233705086-fc12d2a5-6cd9-4321-b487-eff66f8ee401.jpg)
The Mitigation is provided by using Firebase Auth to allow authentication of user in the application. The figure shows that user is verified with their actual email id and their password is hashed on the before being stored in the user database. The code also handles exceptions and error.

3. Improper Platform Usage
Evidence:
The app calculates calorie data based on user input and saves such information in an unencrypted TXT file that can be viewed straight from the phone directory. This data is sensitive, and storing it on internal storage is regarded an insecure method of storing sensitive data since an attacker who obtains access to the device may directly access it.
![5](https://user-images.githubusercontent.com/53125151/233705441-bdc91930-bec5-477b-9bde-1a2ecea1f70c.png)

Mitigation:
 ![6](https://user-images.githubusercontent.com/53125151/233705457-94e3d739-5b07-46a7-bff8-2e98c87659ef.png)

The Firebase database is utilised to save data every time a user calculates their calorie intake; this is especially important when the user maintains their calorie intake based on their weight each day. Figure depicts a database reference being called, computed calorie data being recorded by replacing the previously calculated number.

4. Client Code Quality
Evidence:
The app lacks the input validation and allows user to enter untrusted data, which can result in vulnerability like buffer overflow, SQL injection, XSS. By not validating inputs, an attacker could inject malicious payloads and break the app normal behaviour or the attacker could have control over the entire app, stealing sensitive data, and causing severe damage.
![7](https://user-images.githubusercontent.com/53125151/233705484-b921aa6d-ff86-4f75-a94f-220df90dcc50.jpg)

Mitigation:
![8](https://user-images.githubusercontent.com/53125151/233705509-462ce938-20d7-434e-b0df-44ac036a1548.jpg)
Figure depicts the input validation, which requires the user to enter data. The input is verified for length, and if the user enters an empty field, an error message is displayed instead of the application crashing.

5. Code Tampering
An Android application has debugging enabled. It facilitates reverse engineering by supplying precise information about the application's activity, such as variable values and stack traces. This can help an attacker understand the application's code and identify flaws. Furthermore, it enables an attacker to trace the code and change it in order to uncover flaws in the code.

Mitigation:
 ![9](https://user-images.githubusercontent.com/53125151/233705529-b0cce9d2-e131-4905-9290-55bdac990e64.jpg)
Figure depicts the code used in the application to protect it from reverse engineering. The API keys are secured using the Native Development Kit (NDK). This is accomplished by establishing a separate folder to store C++ file with generated key and then calling that key in the main activity section of the application. Reverse engineering will fail since the NDK is only used while the app is being created and the attacker will not be able to observe that.  The source is available on https://medium.com/@yourdeveloperhere001/secure-your-api-using-ndk-f78e2a34bed1.

