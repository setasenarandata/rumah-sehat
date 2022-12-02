import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';

class MyProfileCard extends StatelessWidget {
  final String username;
  final String nama;
  final String email;
  final int saldo;
  final int umur;

  const MyProfileCard(
    this.username,
    this.nama,
    this.email,
    this.saldo,
    this.umur,
  );

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      decoration: BoxDecoration(
        image: DecorationImage(
            image: AssetImage('assets/images/background-my-profile.png'),
            fit: BoxFit.fill),
      ),
      child: Stack(
        children: <Widget>[
          Container(
            margin: const EdgeInsets.only(
              left: kDefaultPadding * 4,
              right: kDefaultPadding * 4,
              top: kDefaultPadding * 4,
              bottom: kDefaultPadding,
            ),
            padding: EdgeInsets.only(
              left: kDefaultPadding,
              right: kDefaultPadding,
            ),
            child: Column(
              children: <Widget>[
                Image.asset('assets/images/avatar.png'),
                SizedBox(height: 20),
                FittedBox(
                  fit: BoxFit.fitWidth,
                  child: Text(
                    nama,
                    style: Theme.of(context).textTheme.headline5?.copyWith(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                        ),
                  ),
                ),
                SizedBox(height: 10),
                Text(
                  username,
                  style: Theme.of(context).textTheme.bodyText2?.copyWith(
                        color: Colors.grey.shade600,
                        fontWeight: FontWeight.normal,
                      ),
                ),
                SizedBox(height: 10),
                Text(
                  email,
                  style: Theme.of(context).textTheme.caption?.copyWith(
                        color: Colors.grey.shade600,
                        fontWeight: FontWeight.normal,
                      ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
