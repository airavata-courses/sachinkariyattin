import requests
from flask import Flask

app = Flask(__name__)

@app.route('/flask/<username>')
def getUsername(username):
	return requests.get('http://localhost:8080/sciencegateway/webapi/hello/'+username+'hello').content

@app.route("/")
def hello():
    return "Hello World!"
if __name__ == "__main__":
    app.run()