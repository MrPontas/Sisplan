package sisplan

import java.text.SimpleDateFormat 
import java.util.Date

import grails.gsp.PageRenderer

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;



class Mqtt {
    String broker = "tcp://broker.mqtt-dashboard.com:1883";
    String clientId;
    String topicoErro = "Sisplan.irrigacao.erro";
    String topicoUmidade = "Sisplan.irrigacao.umidade";
    String topicoUmidadeAtual = "Sisplan.irrigacao.umidadeAtual";
    int qos = 2;
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient mqttCliente;
    MqttConnectOptions connOpts;
    String umidadeAtual;
    Mqtt(){
        Date date = new Date();
        clientId = date.toString();
        mqttCliente = new MqttClient(broker, clientId, persistence);
        connOpts = new MqttConnectOptions();
    }

    def conectaClienteMqtt(){
        try{
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
            println("Connecting to broker: "+broker);
            mqttCliente.connect(connOpts);
            println("Connected");
        }catch(Exception e) {
            println e
        }       
    }
    def publicaMensagem(String mensagemString, def topico){
        try{
            MqttMessage mensagem = new MqttMessage(mensagemString.getBytes());
            mensagem.setQos(qos);
            String topicoMqtt;
            if(topico == "erro") topicoMqtt = topicoErro
            else if(topico == "umidadeAtual") topicoMqtt = topicoUmidadeAtual
            else topicoMqtt = topicoUmidade
            mqttCliente.publish(topicoMqtt, mensagem);
        } catch(MqttException me){
            println("reason "+me.getReasonCode());
            println("msg "+me.getMessage());
            println("loc "+me.getLocalizedMessage());
            println("cause "+me.getCause());
            // println("excep "+me);
            me.printStackTrace();
        }
    }
    def mqttCallback(){
        println "teste"
    }
    def subscribe(){
        String topico = "Sisplan.irrigacao.umidadeAtual";
        String topicoStatus = "Sisplan.irrigacao.umidadeAtual";

        mqttCliente.setCallback(new MqttCallback() {
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                umidadeAtual = new String(message.getPayload())
                println("\nReceived a Message!" +
                    "\n\tTopic:   " + topic +
                    "\n\tMessage: " + umidadeAtual +
                    "\n\tQoS:     " + message.getQos() + "\n");
                
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
            public void connectionLost(Throwable cause) {
               println("Connection lost because: " + cause);
            }
        });
        mqttCliente.subscribe(topico, qos);
        mqttCliente.subscribe(topicoStatus, qos);

    }
    String getUmidadeAtual(){
        return umidadeAtual
    }
    def desconectaMqtt(){
        mqttCliente.disconnect();
    }
}