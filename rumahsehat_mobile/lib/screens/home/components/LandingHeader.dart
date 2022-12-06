import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';

class LandingHeader extends StatelessWidget {
  const LandingHeader({
    Key? key,
    required this.size,
  }) : super(key: key);

  final Size size;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: size.height * 0.2,
      child: Stack(
        children: <Widget>[
          Container(
            height: size.height * 0.2,
            padding: const EdgeInsets.only(
                left: kDefaultPadding,
                right: kDefaultPadding,
                bottom: 36 + kDefaultPadding),
            decoration: const BoxDecoration(
                color: Colors.red,
                borderRadius: BorderRadius.only(
                    bottomLeft: Radius.circular(44),
                    bottomRight: Radius.circular(44))),
            child: Row(
              children: <Widget>[
                Text(
                  'Hi there!\nWelcome to RumahSehat',
                  style: Theme.of(context).textTheme.headline6?.copyWith(
                      color: Colors.white, fontWeight: FontWeight.bold),
                ),
                const Spacer(),
                Image.asset('assets/images/Logo-only.png'),
              ],
            ),
          )
        ],
      ),
    );
  }
}
