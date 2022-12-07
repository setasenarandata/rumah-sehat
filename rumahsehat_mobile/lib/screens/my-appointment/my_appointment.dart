import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/my-appointment/components/AppointmentCard.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';

class MyAppointment extends StatefulWidget {
  final String username;
  const MyAppointment({Key? key, required this.username}) : super(key: key);

  @override
  State<MyAppointment> createState() => _MyAppointmentState();
}

class _MyAppointmentState extends State<MyAppointment> {
  late Future<List<Appointment>> futureMyAppointment;

  @override
  void initState() {
    super.initState();
    futureMyAppointment = fetchMyAppointment(widget.username);
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
          child: Column(
        children: <Widget>[
          Container(
            height: 400,
            decoration: BoxDecoration(
              image: DecorationImage(
                image:
                    AssetImage('assets/images/background-my-appointment.png'),
                fit: BoxFit.fill,
              ),
            ),
            child: Stack(
              children: <Widget>[
                Container(
                  margin: const EdgeInsets.only(
                    left: kDefaultPadding * 4,
                    right: kDefaultPadding * 4,
                    top: kDefaultPadding * 4,
                    bottom: kDefaultPadding,
                  ),
                  padding: EdgeInsets.only(
                    left: kDefaultPadding,
                    right: kDefaultPadding,
                  ),
                ),
              ],
            ),
          ),
          FutureBuilder<List<Appointment>>(
            future: futureMyAppointment,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                List<Widget> list = [];
                for (var i = 0; i < snapshot.data!.length; i++) {
                  print(snapshot.data![i].kode);
                  list.add(
                    AppointmentCard(
                      size: size,
                      waktu: snapshot.data![i].waktuAwal,
                      colorAccent: Colors.red,
                      kode: snapshot.data![i].kode,
                      nama_dokter: snapshot.data![i].dokter.nama,
                      status: snapshot.data![i].isDone == true
                          ? "Selesai"
                          : "Belum selesai",
                      fungsi: () {},
                    ),
                  );
                  list.add(SizedBox(
                    height: 40,
                  ));
                }
                return Container(
                    child: FittedBox(
                  child: SizedBox(
                    child: Column(
                      children: list,
                    ),
                  ),
                ));
              } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              }

              // By default, show a loading spinner.
              return const Center(
                child: CircularProgressIndicator(),
              );
            },
          ),
        ],
      )),
    );
  }
}

Future<List<Appointment>> fetchMyAppointment(String username) async {
  final response = await http.get(
      Uri.parse('http://localhost:8080/api/v1/list-appointment/' + username));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return parseAppointments(response.body);
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load profile appointments');
  }
}

class Appointment {
  final String kode;
  final bool isDone;
  final String waktuAwal;
  final Pasien pasien;
  final Dokter dokter;
  // tagihanModel
  // resepModel
  const Appointment({
    required this.kode,
    required this.isDone,
    required this.waktuAwal,
    required this.pasien,
    required this.dokter,
  });

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        kode: json["kode"],
        isDone: json["isDone"],
        waktuAwal: json["waktuAwal"],
        pasien: Pasien.fromJson(json["pasien"]),
        dokter: Dokter.fromJson(json["dokter"])
        // pasien: json["pasien"],
        );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data["kode"] = this.kode;
    data["isDone"] = this.isDone;
    data["waktuAwal"] = this.waktuAwal;
    data["pasien"] = this.pasien;
    return data;
  }
}

List<Appointment> parseAppointments(String responseBody) {
  final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();

  return parsed.map<Appointment>((json) => Appointment.fromJson(json)).toList();
}

class Dokter {
  final String id;
  final String username;
  final bool isSso;
  final String nama;
  final String email;
  final String password;
  final String role;
  final int tarif;

  const Dokter(
      {required this.id,
      required this.username,
      required this.isSso,
      required this.nama,
      required this.email,
      required this.password,
      required this.role,
      required this.tarif});

  factory Dokter.fromJson(Map<String, dynamic> json) {
    return Dokter(
      id: json["id"],
      username: json["username"],
      isSso: json["isSso"],
      nama: json["nama"],
      email: json["email"],
      password: json["password"],
      role: json["role"],
      tarif: json["tarif"],
    );
  }
}
