import requests
import pika
from flask import Flask

app = Flask(__name__)

@app.route('/flask/<username>')
def getUsername(username):
	connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
	channel = connection.channel()
	channel.queue_declare(queue='QUEUE1')
	channel.basic_publish(exchange='',
                      routing_key='QUEUE1',
                      body=username+'Flas')
	connection.close()
	# these requests just invoke the RabbitMQ receive function, actual messages are sent through queues
	x = requests.get('http://localhost:7070/JavaMicroservice/webapi/register/reg/sometext').content
	return requests.get('http://localhost:7070/JavaMicroservice/webapi/sendmessage/sometext').content

@app.route("/")
def hello():
    return "Hello World!"
	

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
