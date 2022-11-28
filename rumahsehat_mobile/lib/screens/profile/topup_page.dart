import 'package:flutter/material.dart';

import 'package:flutter_svg/svg.dart';
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/login/registrasi_pasien_page.dart';
import 'package:rumahsehat_mobile/screens/profile/components/MyProfileCard.dart';
import 'package:rumahsehat_mobile/screens/profile/components/ProfileTopup.dart';

import 'components/RSCard.dart';

class TopupPage extends StatelessWidget {
  const TopupPage({Key? key}) : super(key: key);

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
        child: Container(
          child: Column(
            children: <Widget>[
              ProfileTopup(),
            ],
          ),
        ),
      ),
    );
  }
}
