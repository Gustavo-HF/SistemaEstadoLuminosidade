#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

// Configurações de rede do Wokwi
const char* ssid = "Wokwi-GUEST";
const char* password = "";

// SUA URL DO CLOUDFLARE (Já com o endpoint correto)
String serverName = "https://headquarters-thermal-filename-over.trycloudflare.com/sensor/luminosidade";

const int ldrPin = 34; // Pino AO do módulo azul
const int ledPin = 2;  // Entrada de conexão com o pino

void setup() {
  Serial.begin(115200);
  pinMode(ledPin, OUTPUT);
  
  Serial.print("Conectando ao WiFi");
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConectado!");
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    int valorLDR = analogRead(ldrPin);
    Serial.print("LDR Lido: ");
    Serial.println(valorLDR);

    HTTPClient http;
    http.begin(serverName);
    http.addHeader("Content-Type", "application/json");

    // Cria o JSON {"valor": 1234}
    StaticJsonDocument<200> doc;
    doc["valor"] = valorLDR;
    String requestBody;
    serializeJson(doc, requestBody);

    // Envia o POST
    int httpResponseCode = http.POST(requestBody);

    if (httpResponseCode > 0) {
      String response = http.getString();
      Serial.print("Resposta da API: ");
      Serial.println(response);

      // Liga/Desliga o LED físico no Wokwi conforme o retorno da sua lógica Java
      if (response == "ON") {
        digitalWrite(ledPin, HIGH);
      } else {
        digitalWrite(ledPin, LOW);
      }
    } else {
      Serial.print("Erro HTTP: ");
      Serial.println(httpResponseCode);
    }
    http.end();
  }
  
  delay(3000); // Envia dados a cada 3 segundos
}