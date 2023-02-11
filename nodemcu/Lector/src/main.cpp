#include <Arduino.h>
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>
#include <Arduino_JSON.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

#define LED D0 

const int analogInPin = A0;  // ESP8266 Analog Pin ADC0 = A0
float sensorValue = 0;  // value read from the pot

const char* publicarRegistro = "http://192.168.100.7:8080/api/v1.0/lectura";

unsigned long lastRead = 0;

// Set timer to 5 seconds (5000)
unsigned long timerDelay = 50;
char buffer[500];

WiFiManager wiFiManager;

void httpPostRequest(const char* endpoint, float xvalue, float yvalue){           
  sprintf(buffer, "{\"xvalue\":\"%.2f\",\"yvalue\":\"%.2f\"}", xvalue, yvalue);
 
  WiFiClient client;
  HTTPClient http;
  http.begin(client, endpoint);
  
  http.addHeader("Content-Type", "application/json");
  
  int code = http.POST(buffer);
  
  http.end();
}

void setup() {
  Serial.begin(115200);
  pinMode(LED, OUTPUT); 
  wiFiManager.autoConnect("Lector");
  digitalWrite(LED, LOW);
 }

void loop() {
  sensorValue = analogRead(analogInPin);
 
  if((millis() - lastRead) > timerDelay) {

    httpPostRequest(publicarRegistro, millis(), sensorValue);
    lastRead = millis();
  }
}