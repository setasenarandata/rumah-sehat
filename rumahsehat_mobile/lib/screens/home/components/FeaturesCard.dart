import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';

class FeaturesCard extends StatelessWidget {
  const FeaturesCard(
      {Key? key,
      required this.size,
      required this.title,
      required this.press,
      required this.imagePath,
      required this.boxColor})
      : super(key: key);

  final Size size;
  final String title;
  final String imagePath;
  final Color boxColor;
  final void Function() press;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: press,
      child: Container(
        padding: const EdgeInsets.only(
            left: kDefaultPadding, right: kDefaultPadding),
        margin: const EdgeInsets.only(
          left: kDefaultPadding / 2,
          right: kDefaultPadding / 2,
        ),
        decoration: BoxDecoration(
            color: boxColor,
            borderRadius: const BorderRadius.all(Radius.circular(24))),
        height: size.height * 0.2,
        // color: Colors.black,
        child: Row(
          children: <Widget>[
            Text(
              title,
              style: Theme.of(context).textTheme.headline6?.copyWith(
                color: Colors.white,
                fontWeight: FontWeight.bold,
                shadows: <Shadow>[
                  Shadow(
                    offset: const Offset(1.0, 1.0),
                    blurRadius: 40.0,
                    color: Colors.grey.shade600,
                  ),
                ],
              ),
            ),
            const Spacer(),
            Image.asset(imagePath)
          ],
        ),
      ),
    );
  }
}
