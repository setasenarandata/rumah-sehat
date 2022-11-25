import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';

class RegistrasiPasienPage extends StatefulWidget {
  const RegistrasiPasienPage({Key? key}) : super(key: key);

  @override
  State<RegistrasiPasienPage> createState() => _RegistrasiPasienPageState();
}

class _RegistrasiPasienPageState extends State<RegistrasiPasienPage> {
  TextEditingController username_controller = TextEditingController();
  TextEditingController nama_controller = TextEditingController();
  TextEditingController email_controller = TextEditingController();
  TextEditingController password_controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Registrasi Pasien"),
      ),
      body: Center(
        child: ListView(
          shrinkWrap: true,
          children: <Widget>[
            Container(
              // heading TambahObat
              height: 100,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text(
                    "Registrasi",
                    style: TextStyle(
                        fontSize: 30,
                        color: Colors.amber,
                        fontWeight: FontWeight.w700),
                  ),
                  Text(
                    "Pasien",
                    style: TextStyle(
                        fontSize: 30,
                        color: Colors.indigo,
                        fontWeight: FontWeight.w700),
                  ),
                ],
              ),
            ),
            Container(
              margin: EdgeInsets.all(10),
              child: TextField(
                // field username
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(5),
                  ),
                  labelText: "Username",
                ),
                controller: username_controller,
                onChanged: (value) {
                  setState(() {});
                },
              ),
            ),
            Container(
              margin: EdgeInsets.all(10),
              child: TextField(
                // field nama
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(5),
                  ),
                  labelText: "Nama Lengkap",
                ),
                controller: nama_controller,
                onChanged: (value) {
                  setState(() {});
                },
              ),
            ),
            Container(
              margin: EdgeInsets.all(10),
              child: TextField(
                // field email
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(5),
                  ),
                  labelText: "Email",
                ),
                controller: email_controller,
                onChanged: (value) {
                  setState(() {});
                },
              ),
            ),
            Container(
              margin: EdgeInsets.all(10),
              child: TextField(
                // field password
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(5),
                  ),
                  labelText: "Password",
                ),
                controller: password_controller,
                onChanged: (value) {
                  setState(() {});
                },
              ),
            ),
            Container(
              // button tambahkan
              margin: EdgeInsets.all(10),
              child: ElevatedButton(
                style: ButtonStyle(
                  backgroundColor:
                      MaterialStatePropertyAll<Color>(Colors.indigo),
                ),
                child: Text(
                  "Tambahkan",
                  style: TextStyle(
                    color: Colors.white,
                  ),
                ),
                onPressed: () async {
                  // post
                  create_pasien(username_controller.text, nama_controller.text,
                      email_controller.text, password_controller.text);
                  // Navigator push
                  Navigator.pushReplacement(context,
                      MaterialPageRoute(builder: (context) {
                    return ProfilePage();
                  }));
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}

Future<void> create_pasien(
    String username, String nama, String email, String password) async {
  http
      .post(
        Uri.parse('http://localhost:8000/api/v1/user/add'),
        // headers: <String, String>{
        //   'Content-Type': 'application/json; charset=UTF-8',
        // },
        body: jsonEncode(<String, dynamic>{
          "username": username,
          "isSso": false,
          "nama": nama,
          "email": email,
          "password": password,
          "role": {
            "id": 4,
          }
        }),
      )
      .then((response) => print(response.body))
      .catchError((error) => print(error));
}
