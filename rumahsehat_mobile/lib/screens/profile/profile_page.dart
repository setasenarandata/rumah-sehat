import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_svg/svg.dart';
import 'package:rumahsehat_mobile/screens/profile/registrasi_pasien_page.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text("My Profile"),
          elevation: 0,
          leading: IconButton(
              onPressed: () {},
              icon: SvgPicture.asset('assets/icons/menu_duo_alt.svg'))),
      body: Center(
          child: ElevatedButton(
        child: Text("Registrasi Pasien"),
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) {
            return RegistrasiPasienPage();
          }));
        },
      )),
    );
  }
}
