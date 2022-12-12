import 'dart:convert';
// import 'dart:ffi';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/screens/home/home_page.dart';
import 'package:rumahsehat_mobile/screens/my-appointment/my_appointment.dart';

class ScheduleAppointment extends StatefulWidget {
  final String username;
  const ScheduleAppointment({Key? key, required this.username})
      : super(key: key);

  @override
  State<ScheduleAppointment> createState() => _ScheduleAppointmentState();
}

class _ScheduleAppointmentState extends State<ScheduleAppointment> {
  TextEditingController time_controller = TextEditingController();
  String _valDokter = "";
  DateTime dateTime = DateTime(2022, 12, 7, 5, 30);
  late Future<List<Dokter>> listDoctors;

  @override
  void initState() {
    super.initState();
    listDoctors = fetchListDoctor();
  }

  @override
  Widget build(BuildContext context) {
    final hours = dateTime.hour.toString().padLeft(2, '0');
    final minutes = dateTime.minute.toString().padLeft(2, '0');
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: BackButton(
          onPressed: () => Navigator.pop(context),
        ),
      ),
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Container(
          child: Column(
            children: <Widget>[
              Container(
                height: 400,
                decoration: BoxDecoration(
                  image: DecorationImage(
                      image: AssetImage(
                          'assets/images/background-schedule-appointment.png'),
                      fit: BoxFit.fill),
                ),
              ),
              Padding(
                padding: EdgeInsets.all(30.0),
                child: Column(
                  children: <Widget>[
                    Container(
                      padding: EdgeInsets.all(5),
                      decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(10),
                          boxShadow: [
                            BoxShadow(
                                color: Color.fromRGBO(143, 148, 251, .2),
                                blurRadius: 20.0,
                                offset: Offset(0, 10))
                          ]),
                      child: Column(
                        children: <Widget>[
                          Container(
                            padding: EdgeInsets.all(8.0),
                            decoration: BoxDecoration(
                              border: Border(
                                bottom: BorderSide(color: Colors.grey.shade100),
                              ),
                            ),
                            child: ElevatedButton(
                              child: Text(
                                  '${dateTime.year}-${dateTime.month}-${dateTime.day}'),
                              onPressed: () async {
                                final date = await pickDate();

                                if (date == null) return;

                                final newDateTime = DateTime(
                                  date.year,
                                  date.month,
                                  date.day,
                                  dateTime.hour,
                                  dateTime.second,
                                );

                                setState(() => dateTime = newDateTime);
                              },
                            ),
                          ),
                          Container(
                            padding: EdgeInsets.all(8.0),
                            decoration: BoxDecoration(
                              border: Border(
                                bottom: BorderSide(color: Colors.grey.shade100),
                              ),
                            ),
                            child: ElevatedButton(
                              child: Text(
                                '${hours}:${minutes}',
                              ),
                              onPressed: () async {
                                final time = await pickTime();

                                if (time == null) return;

                                final newDateTime = DateTime(
                                  dateTime.year,
                                  dateTime.month,
                                  dateTime.day,
                                  time.hour,
                                  time.minute,
                                );

                                setState(() => dateTime = newDateTime);
                                print(dateTime);
                              },
                            ),
                          ),
                          FutureBuilder<List<Dokter>>(
                            future: listDoctors,
                            builder: (context, snapshot) {
                              if (snapshot.hasData) {
                                List<Dokter> dropList = snapshot.data!;
                                print("Dokter length: " +
                                    dropList.length.toString());
                                return DropdownButton(
                                  hint: Text("Select Doctor"),
                                  value: _valDokter != "" ? _valDokter : null,
                                  items: dropList.map((item) {
                                    return DropdownMenuItem(
                                      child: new Text(item.nama),
                                      value: item.username,
                                    );
                                  }).toList(),
                                  // controller: time_controller,
                                  onChanged: (value) {
                                    setState(() {
                                      _valDokter = value.toString();
                                      print("Dokter terpilih:" + _valDokter);
                                    });
                                  },
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
                      ),
                    ),
                    SizedBox(
                      height: 30,
                    ),
                    InkWell(
                      onTap: () async {
                        print(_valDokter);
                        String dateFormat =
                            dateTime.toString().substring(0, 16);
                        print(dateFormat);
                        create_appointment(
                            widget.username, _valDokter, dateFormat);

                        Navigator.pushReplacement(
                          context,
                          MaterialPageRoute(
                            builder: (context) {
                              return HomePage(
                                username: widget.username,
                              );
                            },
                          ),
                        );
                      },
                      child: Container(
                        height: 50,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(10),
                          gradient: LinearGradient(
                            colors: [
                              Color.fromRGBO(255, 168, 205, 0.8),
                              Color.fromRGBO(255, 108, 169, .6),
                            ],
                          ),
                        ),
                        child: Center(
                          child: Text(
                            "Book",
                            style: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  Future<DateTime?> pickDate() => showDatePicker(
        context: context,
        initialDate: dateTime,
        firstDate: DateTime(1900),
        lastDate: DateTime(2100),
      );

  Future<TimeOfDay?> pickTime() => showTimePicker(
      context: context,
      initialTime: TimeOfDay(hour: dateTime.hour, minute: dateTime.minute));

  Future pickDateTime() async {
    DateTime? date = await pickDate();
    if (date == null) return;

    TimeOfDay? time = await pickTime();
    if (time == null) return;

    final dateTime =
        DateTime(date.year, date.month, date.day, time.hour, time.minute);

    setState(() => this.dateTime = dateTime);
  }
}

Future<void> create_appointment(
    String username, String nama_dokter, String waktu) async {
  print(username);
  print(nama_dokter);
  print(waktu);
  http
      .post(
        Uri.parse('http://localhost:8080/api/v1/appointment'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(<String, dynamic>{
          "username": username,
          "dokter": nama_dokter,
          "time": waktu
        }),
      )
      .then((response) => print(response.body))
      .catchError((error) => print(error));
}

List<Dokter> parseDoctor(String responseBody) {
  final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();

  return parsed.map<Dokter>((json) => Dokter.fromJson(json)).toList();
}

Future<List<Dokter>> fetchListDoctor() async {
  final response =
      await http.get(Uri.parse('http://localhost:8080/api/v1/list-doctor/'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return parseDoctor(response.body);
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load profile appointments');
  }
}
