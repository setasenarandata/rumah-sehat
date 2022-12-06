import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';

class ProfileTopup extends StatelessWidget {
  const ProfileTopup({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      decoration: BoxDecoration(
        image: DecorationImage(
            image: AssetImage('assets/images/background-topup.png'),
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
          ),
        ],
      ),
    );
  }
}
