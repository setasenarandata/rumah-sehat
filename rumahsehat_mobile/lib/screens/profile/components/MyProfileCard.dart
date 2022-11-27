import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';

class MyProfileCard extends StatelessWidget {
  const MyProfileCard({
    Key? key,
  }) : super(key: key);

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
                Text(
                  "Mr. Sena",
                  style: Theme.of(context).textTheme.headline5?.copyWith(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                ),
                Text(
                  "Member since Nov 27, 2022",
                  style: Theme.of(context).textTheme.bodyText2?.copyWith(
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