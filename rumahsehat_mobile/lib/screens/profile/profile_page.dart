import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/login/login_page.dart';
import 'package:rumahsehat_mobile/screens/profile/components/MyProfileCard.dart';
import 'package:http/http.dart' as http;

import 'components/RSCard.dart';

class ProfilePage extends StatefulWidget {
  final String username;
  const ProfilePage({Key? key, required this.username}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late Future<Pasien> futurePasien;

  @override
  void initState() {
    super.initState();
    futurePasien = fetchMyProfile(widget.username);
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: BackButton(
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: SingleChildScrollView(
        child: FutureBuilder<Pasien>(
          future: futurePasien,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return Container(
                child: Column(
                  children: <Widget>[
                    MyProfileCard(
                        snapshot.data!.username,
                        snapshot.data!.nama,
                        snapshot.data!.email,
                        snapshot.data!.saldo,
                        snapshot.data!.umur),
                    RSCard(
                      size: size,
                      saldo: snapshot.data!.saldo,
                      colorAccent: Colors.yellow.shade700,
                      username: widget.username,
                    ),
                    const SizedBox(height: 80),
                    RSCard(
                      size: size,
                      saldo: snapshot.data!.saldo,
                      colorAccent: Colors.green.shade700,
                      username: widget.username,
                    ),
                    const SizedBox(height: 80),
                    Container(
                      width: size.width * 0.5,
                      height: size.height * 0.05,
                      child: ElevatedButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => Loginpage()),
                          );
                          ;
                        },
                        child: Text("Logout"),
                      ),
                    ),
                    const SizedBox(
                      height: 100,
                    )
                  ],
                ),
              );
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }

            // By default, show a loading spinner.
            return const Center(
              child: CircularProgressIndicator(),
            );
          },
        ),
      ),
    );
  }
}

Future<Pasien> fetchMyProfile(String username) async {
  final response = await http
      .get(Uri.parse('http://localhost:8080/api/v1/pasien/' + username));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return Pasien.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load profile pasien');
  }
}

class Pasien {
  final String id;
  final String username;
  final bool isSso;
  final String nama;
  final String email;
  final String password;
  final String role;
  final int saldo;
  final int umur;

  const Pasien({
    required this.id,
    required this.username,
    required this.isSso,
    required this.nama,
    required this.email,
    required this.password,
    required this.role,
    required this.saldo,
    required this.umur,
  });

  factory Pasien.fromJson(Map<String, dynamic> json) {
    return Pasien(
      id: json["id"],
      username: json["username"],
      isSso: json["isSso"],
      nama: json["nama"],
      email: json["email"],
      password: json["password"],
      role: json["role"],
      saldo: json["saldo"],
      umur: json["umur"],
    );
  }
}
