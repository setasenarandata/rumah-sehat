import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';

import 'FeaturesCard.dart';
import 'LandingHeader.dart';

class Body extends StatelessWidget {
  const Body({Key? key}) : super(key: key);

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
            press: () {},
            imagePath: 'assets/images/ScheduleAppointment.png',
            boxColor: Colors.green.shade200,
          ),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "My\nAppointment",
            press: () {},
            imagePath: 'assets/images/MyAppointment.png',
            boxColor: Colors.purple.shade200,
          ),
          const SizedBox(
            height: 24,
          ),
          FeaturesCard(
            size: size,
            title: "My Profile",
            press: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return ProfilePage();
              }));
            },
            imagePath: 'assets/images/MyProfile.png',
            boxColor: Colors.blue.shade300,
          )
        ],
      ),
    );
  }
}
