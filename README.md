
## Install flask
```pip install flask```

## Install Express Gateway
http://www.express-gateway.io/

## Configure
Build and deploy the science gateway war in the glassfish web server instance
Deploy hello.py ```hello.py flask run```
Deploy the UI files in any of the websever instances

Once deployed, go to ```apigateway\config\gateway.config``` and make the changes to the following section basedon the deployed port numbers

``` apiEndpoints:
  flaskmicro:
    host: '*'
    paths: '/flask/*'
  javamicro:
    host: '*'
    paths: '/sciencegateway/webapi/hello/*'
  dbmicroinsert:
    host: '*'
    paths: '/sciencegateway/webapi/register/*'
  dbmicroget:
    host: '*'
    paths: '/sciencegateway/webapi/register'
  uitarget:
    host: '*'
    paths: '/*'
    
serviceEndpoints:
  inputendpoint:
    url: 'http://localhost:5000'
  micro2:
    url: 'http://localhost:9090'
  ui:
    url: 'http://127.0.0.1:53238 
 ```
    
  ## Create MySQL database instance and table
  Create database with name registry and then create a table with name "logservice" with a single column "messages"
  
  ## Working
  Input a keyword in ApiResponse.html and click on submit. The first microervice appends the word "Hello" to the string and the second microservice appends the word "Word" and finaly the third micro service saves it in the database
  Clicking on Display.html will fetch all the messages throgh registry service
