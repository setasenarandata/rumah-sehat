import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/home/home_page.dart';
import 'package:rumahsehat_mobile/screens/login/login_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          scaffoldBackgroundColor: kBackgroundColor,
          primarySwatch: Colors.red,
          textTheme: Theme.of(context).textTheme.apply(bodyColor: kTextColor)),
      title: 'RumahSehat App',
      home: Loginpage(),
    );
  }
}
