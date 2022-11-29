import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/profile/components/MyProfileCard.dart';

import 'components/RSCard.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    const String saldo = "1.000.000";
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
        child: Container(
          child: Column(
            children: <Widget>[
              MyProfileCard(),
              RSCard(
                size: size,
                saldo: "1000000",
                colorAccent: Colors.yellow.shade700,
              ),
              const SizedBox(height: 80),
              RSCard(
                size: size,
                saldo: "0",
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
        ),
      ),
    );
  }
}
