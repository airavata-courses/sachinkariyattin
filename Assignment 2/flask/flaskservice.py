import requests
import pika
from flask import Flask

app = Flask(__name__)

@app.route('/flask/<username>')
def getUsername(username):
	credentials = pika.PlainCredentials('mqadmin', 'mqadminpassword')
	parameters = pika.ConnectionParameters('149.165.157.125', 5672, '/', credentials)
	connection = pika.BlockingConnection(parameters)
	channel = connection.channel()
	channel.queue_declare(queue='QUEUE1')
	channel.basic_publish(exchange='',
                      routing_key='QUEUE1',
                      body=username+'Flas')
	connection.close()
	return "Sent "+ username

@app.route("/")
def hello():
    return "Hello World!"
	

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
