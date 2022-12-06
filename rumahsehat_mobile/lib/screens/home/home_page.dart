import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:rumahsehat_mobile/screens/home/components/body.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: buildAppBar(),
      body: Body(),
    );
  }

  AppBar buildAppBar() {
    return AppBar(
        elevation: 0,
        leading: IconButton(
            onPressed: () {},
            icon: SvgPicture.asset('assets/icons/menu_duo_alt.svg')));
  }
}
