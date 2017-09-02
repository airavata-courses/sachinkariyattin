
## Install flask
```pip install flask```

## Install Express Gateway
http://www.express-gateway.io/

## Configure
Build and deploy the science gateway war in the glassfish web server instance
Deploy hello.py ```hello.py flask run```
Deploy the UI files in any of the websever instances

Refer the project wiki https://github.com/airavata-courses/sachinkariyattin/wiki
    
  ## Create MySQL database instance and table
  Create database with name registry and then create a table with name "logservice" with a single column "messages"
  
  ## Working
  Input a keyword in ApiResponse.html and click on submit. The first microervice appends the word "Hello" to the string and the second microservice appends the word "Word" and finaly the third micro service saves it in the database
  Clicking on Display.html will fetch all the messages throgh registry service
