import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/profile/components/MyProfileCard.dart';
import 'package:http/http.dart' as http;

import 'components/RSCard.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late Future<Pasien> futurePasien;

  @override
  void initState() {
    super.initState();
    futurePasien = fetchMyProfile("402892eb84c828d90184c84754550002");
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
                    ),
                    const SizedBox(height: 80),
                    RSCard(
                      size: size,
                      saldo: snapshot.data!.saldo,
                      colorAccent: Colors.green.shade700,
                    ),
                    // Container(
                    //   height: 400,
                    //   color: Colors.black,
                    //   child: Stack(
                    //     children: <Widget>[],
                    //   ),
                    // )
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

Future<Pasien> fetchMyProfile(String id) async {
  final response =
      await http.get(Uri.parse('http://localhost:8080/api/v1/pasien/' + id));

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

// "id": "402892eb84c828d90184c84754550002",
// "username": "setasena",
// "isSso": false,
// "nama": "Setasena Randata",
// "email": "setasena93@gmail.com",
// "password": "$2a$10$ZAxCAI6Q9YtSluPQFSMMyeEoHhndCo5R4CXGzTHqk5d2Yb5xARcZm",
// "role": "Pasien",
// "saldo": 0,
// "umur": 21