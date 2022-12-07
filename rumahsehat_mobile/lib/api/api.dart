import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class Api{
  static final String url = "http://localhost:8080/api/";
  static final dio = Dio(BaseOptions(baseUrl: url));
  
  static Future<Map> login(String username, String password) async{
    Uri uri = Uri.parse('${url}v1/login/');
    final response = await http.post(
      uri,
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{'username': username, 'password': password}),
    );
    if (response.statusCode == 200) {
      final Map parsedResponse = json.decode(response.body);
      final String token = parsedResponse['jwttoken'];
      bool isTokenExpired = JwtDecoder.isExpired(token);
      if (!isTokenExpired) {
        return parsedResponse;
      }
    }
    return {
      "jwttoken": "Failed"
    };
  }
}
