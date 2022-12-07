import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/screens/profile/topup_page.dart';

class AppointmentCard extends StatelessWidget {
  const AppointmentCard({
    Key? key,
    required this.size,
    required this.waktu,
    required this.colorAccent,
    required this.kode,
    required this.nama_dokter,
    required this.status,
    required this.fungsi,
  }) : super(key: key);

  final Size size;
  final String waktu;
  final Color colorAccent;
  final String kode;
  final String nama_dokter;
  final String status;
  final Function() fungsi;

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
                  Text(
                    'Appointment ${kode}',
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 18,
                        color: Colors.white),
                  ),
                  const SizedBox(height: 10),
                  Text(
                    'Status: ${status}',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 12,
                      color: Colors.white,
                    ),
                  ),
                  const SizedBox(height: 10),
                  Text(
                    'Waktu: ${waktu}',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 12,
                      color: Colors.white,
                    ),
                  ),
                  const SizedBox(height: 40),
                  Text(
                    'Dr. ${nama_dokter}',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                      color: Colors.white,
                    ),
                  ),
                  const SizedBox(height: 10),
                  Text(
                    'Status: ${status}',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 12,
                      color: Colors.white,
                    ),
                  ),
                ],
              ),
            ),
            InkWell(
              onTap: fungsi,
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
                    Container(
                      padding: const EdgeInsets.all(10),
                      margin: const EdgeInsets.only(top: 10),
                      child: Image.asset('assets/images/female_doctor.png',
                          width: 120, height: 100, fit: BoxFit.cover),
                    ),
                    // const Spacer(),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
