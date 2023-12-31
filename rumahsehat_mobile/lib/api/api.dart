import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class Api {
  static final String url = "https://apap-104.cs.ui.ac.id/api/";
  static final dio = Dio(BaseOptions(baseUrl: url));

  static Future<Map> login(String username, String password) async {
    print("INSIDE LOGIN ASYNC");
    print("username: " + username);
    print("password" + password);
    Uri uri = Uri.parse('${url}v1/login/');
    final response = await http.post(
      uri,
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(
          <String, String>{'username': username, 'password': password}),
    );
    if (response.statusCode == 200) {
      final Map parsedResponse = json.decode(response.body);
      final String token = parsedResponse['jwttoken'];
      bool isTokenExpired = JwtDecoder.isExpired(token);
      if (!isTokenExpired) {
        return parsedResponse;
      }
    } else if (response.statusCode == 403) {
      return {"jwttoken": "Unauthorized"};
    }
    return {"jwttoken": "Failed"};
  }
}
