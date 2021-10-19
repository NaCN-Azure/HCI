import json

import judge
from flask import Flask, jsonify, request

app = Flask(__name__)

@app.after_request
def cors(environ):
    environ.headers['Access-Control-Allow-Origin']='*'
    environ.headers['Access-Control-Allow-Method']='*'
    environ.headers['Access-Control-Allow-Headers']='x-requested-with,content-type'
    return environ

@app.route('/getQuestionDetails', methods=['post'])
def getQuestionDetails():
    recv_data = request.get_data()
    print(recv_data)
    if recv_data:
        json_re = json.loads(recv_data)
    else:
        print("receive data is empty")

    question = json_re["question"]
    return jsonify(judge.predict(question))


app.run(host='0.0.0.0', port=8802, debug=True)
