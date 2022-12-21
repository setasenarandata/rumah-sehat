import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/my-appointment/components/AppointmentCard.dart';
import 'package:rumahsehat_mobile/screens/my-appointment/my_appointment.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';

class AppointmentDetail extends StatefulWidget {
  final String kode;
  final String nama_pasien;
  const AppointmentDetail(
      {Key? key, required this.kode, required this.nama_pasien})
      : super(key: key);

  @override
  State<AppointmentDetail> createState() => _AppointmentDetailState();
}

class _AppointmentDetailState extends State<AppointmentDetail> {
  late Future<Appointment> futureAppointmentDetail;

  @override
  void initState() {
    super.initState();
    futureAppointmentDetail = fetchMyAppointment(widget.kode);
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
                image: AssetImage(
                    'assets/images/background-appointment-details.png'),
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
          FutureBuilder<Appointment>(
            future: futureAppointmentDetail,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                String _status =
                    snapshot.data!.isDone == true ? "Selesai" : "Belum selesai";
                return FittedBox(
                  child: SizedBox(
                    height: size.height * 0.30,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Container(
                          width: size.width * 0.80,
                          padding: const EdgeInsets.fromLTRB(16, 10, 0, 20),
                          decoration: BoxDecoration(
                            borderRadius: const BorderRadius.horizontal(
                              left: Radius.circular(15),
                              right: Radius.circular(15),
                            ),
                            color: Colors.black,
                          ),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                'Appointment ${widget.kode}',
                                style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 24,
                                    color: Colors.white),
                              ),
                              const SizedBox(
                                height: 10,
                              ),
                              Text(
                                'Pasien: ${widget.nama_pasien}',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                  color: Colors.white,
                                ),
                              ),
                              const SizedBox(
                                height: 10,
                              ),
                              Text(
                                'Dr. ${snapshot.data!.dokter.nama}',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                  color: Colors.white,
                                ),
                              ),
                              const SizedBox(height: 10),
                              Text(
                                'Tarif: ${snapshot.data!.dokter.tarif}',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 12,
                                  color: Colors.white,
                                ),
                              ),
                              const SizedBox(height: 10),
                              Text(
                                'Status: ${_status}',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 12,
                                  color: Colors.white,
                                ),
                              ),
                              const SizedBox(height: 10),
                              Text(
                                'Waktu: ${snapshot.data!.waktuAwal}',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 12,
                                  color: Colors.white,
                                ),
                              ),
                              const SizedBox(
                                height: 10,
                              ),
                              ElevatedButton(
                                  onPressed: () {}, child: Text("Detail Resep"))
                            ],
                          ),
                        ),
                      ],
                    ),
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
        ],
      )),
    );
  }
}

Future<Appointment> fetchMyAppointment(String kode) async {
  final response = await http.get(
      Uri.parse('https://apap-104.cs.ui.ac.id/api/v1/appointment/' + kode));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return Appointment.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load profile appointments');
  }
}
