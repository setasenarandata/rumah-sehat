import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:rumahsehat_mobile/screens/home/components/body.dart';

class HomePage extends StatelessWidget {
  final String username;
  const HomePage({Key? key, required this.username}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: buildAppBar(),
      body: Body(username: username),
    );
  }

  AppBar buildAppBar() {
    return AppBar(
      elevation: 0,
      leading: IconButton(
        onPressed: () {},
        icon: SvgPicture.asset('assets/icons/menu_duo_alt.svg'),
      ),
    );
  }
}
