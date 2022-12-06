import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/login/components/BodyLogin.dart';

class Loginpage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      body: BodyLogin(),
    );
  }
}
