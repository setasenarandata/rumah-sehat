import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/profile/topup_page.dart';

class RSCard extends StatelessWidget {
  const RSCard({
    Key? key,
    required this.size,
    required this.saldo,
    required this.colorAccent,
  }) : super(key: key);

  final Size size;
  final String saldo;
  final Color colorAccent;

  @override
  Widget build(BuildContext context) {
    return FittedBox(
      child: SizedBox(
        height: size.height * 0.23,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: size.width * 0.60,
              padding: const EdgeInsets.fromLTRB(16, 10, 0, 20),
              decoration: BoxDecoration(
                borderRadius:
                    const BorderRadius.horizontal(left: Radius.circular(15)),
                color: Colors.black,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Image.asset('assets/images/rumahsehat_yellow.png',
                      width: 60, height: 50, fit: BoxFit.cover),
                  const SizedBox(height: 10),
                  Text(
                    'IDR ${saldo}',
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 24,
                        color: Colors.white),
                  ),
                  const SizedBox(height: 20),
                  Text('CARD NUMBER',
                      style: TextStyle(
                          color: Colors.white.withOpacity(0.5), fontSize: 12)),
                  const SizedBox(height: 15),
                  const Text('**** **** **** 2111',
                      style: TextStyle(color: Colors.white, fontSize: 15)),
                ],
              ),
            ),
            InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => TopupPage()),
                );
              },
              child: Container(
                width: size.width * 0.27,
                decoration: BoxDecoration(
                  borderRadius:
                      const BorderRadius.horizontal(right: Radius.circular(15)),
                  color: colorAccent,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('Top-up',
                        style: TextStyle(
                            fontSize: 12, fontWeight: FontWeight.bold)),
                    Container(
                      padding: const EdgeInsets.all(10),
                      margin: const EdgeInsets.only(top: 10),
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: Colors.black,
                      ),
                      child: const Icon(
                        Icons.add,
                        color: Colors.white,
                        size: 20,
                      ),
                    ),
                    // const Spacer(),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
