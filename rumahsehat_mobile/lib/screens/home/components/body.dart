import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/my-appointment/my_appointment.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';
import 'package:rumahsehat_mobile/screens/schedule-appointment/schedule_appointment.dart';
import 'package:rumahsehat_mobile/screens/bills/daftar_tagihan.dart';

import 'FeaturesCard.dart';
import 'LandingHeader.dart';

class Body extends StatelessWidget {
  final String username;
  const Body({Key? key, required this.username}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SingleChildScrollView(
      child: Column(
        children: <Widget>[
          LandingHeader(size: size),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "Schedule\nAppointment",
            press: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ScheduleAppointment(
                  username: username,
                );
              }));
            },
            imagePath: 'assets/images/ScheduleAppointment.png',
            boxColor: Colors.green.shade200,
          ),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "My\nAppointment",
            press: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return MyAppointment(
                  username: username,
                );
              }));
            },
            imagePath: 'assets/images/MyAppointment.png',
            boxColor: Colors.purple.shade200,
          ),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "My Bills",
            press: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return DaftarTagihanPage();
              }));
            },
            imagePath: 'assets/images/MyProfile.png',
            boxColor: Colors.blue.shade200,
          ),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "My Profile",
            press: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ProfilePage(
                  username: username,
                );
              }));
            },
            imagePath: 'assets/images/MyProfile.png',
            boxColor: Colors.yellow.shade700,
          ),
          const SizedBox(
            height: 32,
          ),
        ],
      ),
    );
  }
}
