package services;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import utils.JsonReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.RestAssured;


import io.restassured.response.Response;



public class WireMockManager {
    public static WireMockServer wiremockServer;

    public static void StartWiremock(){
        wiremockServer = new WireMockServer(9089);
        wiremockServer.start();
        WireMock.configureFor("localhost",9089);

    }

    public static void stopWiremockServer() {

        wiremockServer.stop();
    }



}