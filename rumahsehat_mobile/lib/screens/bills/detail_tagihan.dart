import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:intl/intl.dart';
import 'package:rumahsehat_mobile/models/tagihanModel.dart';
import 'package:rumahsehat_mobile/screens/bills/daftar_tagihan.dart';
import 'package:rumahsehat_mobile/screens/home/home_page.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:rumahsehat_mobile/screens/tagihan/view_all_tagihan.dart';

class DetailTagihanPage extends StatefulWidget {
  final String kode;
  final String username;
  const DetailTagihanPage(
      {Key? key, required this.kode, required this.username})
      : super(key: key);

  @override
  State<DetailTagihanPage> createState() => _DetailTagihanPageState();
}

class _DetailTagihanPageState extends State<DetailTagihanPage> {
  late Future<Tagihan> futureTagihan;
  late Future<Pasien> futurePasien;

  @override
  void initState() {
    super.initState();
    futureTagihan = fetchDetailTagihan(widget.kode);
    futurePasien = fetchMyProfile(widget.username);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: BackButton(
          onPressed: () => Navigator.pushReplacement(
            context,
            MaterialPageRoute(
              builder: (context) => ViewAllTagihan(username: widget.username),
            ),
          ),
        ),
      ),
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Container(
          child: Column(
            children: <Widget>[
              Container(
                height: 400,
                decoration: BoxDecoration(
                  image: DecorationImage(
                      image: AssetImage(
                          'assets/images/background-appointment-details.png'),
                      fit: BoxFit.fill),
                ),
                child: Stack(
                  children: <Widget>[],
                ),
              ),
              Padding(
                padding:
                    EdgeInsets.only(bottom: 30.00, left: 30.00, right: 30.00),
                child: FutureBuilder<Tagihan>(
                    future: futureTagihan,
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return Column(
                          children: <Widget>[
                            Container(
                              width: MediaQuery.of(context).size.width,
                              height: MediaQuery.of(context).size.width,
                              decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(20),
                                  color: Colors.white,
                                  boxShadow: <BoxShadow>[
                                    BoxShadow(
                                        color: Color(0xFFEAEAEA),
                                        offset: Offset.zero,
                                        blurRadius: 5.0,
                                        spreadRadius: 5.0)
                                  ]),
                              child: Column(children: [
                                Expanded(
                                  child: Container(
                                    decoration: BoxDecoration(
                                      borderRadius: BorderRadius.only(
                                          topLeft: Radius.circular(20),
                                          topRight: Radius.circular(20)),
                                    ),
                                    child: Row(
                                      children: [
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(left: 15),
                                            child: Text(
                                              'Kode',
                                              style: TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(right: 15),
                                            child: Text(
                                              snapshot.data!.kode,
                                              style: TextStyle(
                                                // fontWeight: FontWeight.bold,
                                                fontSize: 14,
                                              ),
                                              textAlign: TextAlign.end,
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                                Expanded(
                                  child: Container(
                                    decoration: BoxDecoration(
                                        border: Border(
                                            top: BorderSide(
                                                color: Color(0xFFEAEAEA)),
                                            bottom: BorderSide(
                                                color: Color(0xFFEAEAEA)))),
                                    child: Row(
                                      children: [
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(left: 15),
                                            child: Text(
                                              'Waktu pembuatan',
                                              style: TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(right: 15),
                                            child: Text(
                                              DateFormat()
                                                  .add_yMd()
                                                  .add_jm()
                                                  .format(DateTime.parse(
                                                      snapshot.data!
                                                          .tanggalTerbuat)),
                                              style: TextStyle(
                                                // fontWeight: FontWeight.bold,
                                                fontSize: 14,
                                              ),
                                              textAlign: TextAlign.end,
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                                Expanded(
                                  child: Container(
                                    decoration: BoxDecoration(
                                        border: Border(
                                            bottom: BorderSide(
                                                color: Color(0xFFEAEAEA)))),
                                    child: Row(
                                      children: [
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(left: 15),
                                            child: Text(
                                              'Waktu pembayaran',
                                              style: TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(right: 15),
                                            child: Text(
                                              DateFormat()
                                                  .add_yMd()
                                                  .add_jm()
                                                  .format(DateTime.parse(
                                                      snapshot.data!
                                                          .tanggalTerbuat)),
                                              style: TextStyle(
                                                fontSize: 14,
                                              ),
                                              textAlign: TextAlign.end,
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                                Expanded(
                                  child: Container(
                                    decoration: BoxDecoration(
                                        border: Border(
                                            bottom: BorderSide(
                                                color: Color(0xFFEAEAEA)))),
                                    child: Row(
                                      children: [
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(left: 15),
                                            child: Text(
                                              'Status',
                                              style: TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(right: 15),
                                            child: (snapshot.data!.isPaid)
                                                ? Text(
                                                    "Lunas",
                                                    style: TextStyle(
                                                      // fontWeight: FontWeight.bold,
                                                      fontSize: 14,
                                                    ),
                                                    textAlign: TextAlign.end,
                                                  )
                                                : Text(
                                                    "Belum Lunas",
                                                    style: TextStyle(
                                                      // fontWeight: FontWeight.bold,
                                                      fontSize: 14,
                                                    ),
                                                    textAlign: TextAlign.end,
                                                  ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                                Expanded(
                                  child: Container(
                                    decoration: BoxDecoration(
                                        border: Border(
                                            bottom: BorderSide(
                                                color: Color(0xFFEAEAEA)))),
                                    child: Row(
                                      children: [
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(left: 15),
                                            child: Text(
                                              'Jumlah tagihan',
                                              style: TextStyle(
                                                  fontWeight: FontWeight.bold,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        Expanded(
                                          child: Container(
                                            padding: EdgeInsets.only(right: 15),
                                            child: Text(
                                              snapshot.data!.jumlahTagihan
                                                  .toString(),
                                              style: TextStyle(
                                                // fontWeight: FontWeight.bold,
                                                fontSize: 14,
                                              ),
                                              textAlign: TextAlign.end,
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                              ]),
                            ),
                            FutureBuilder<Pasien>(
                              future: futurePasien,
                              builder: ((contextP, snapshotP) {
                                if (snapshot.data!.isPaid == false) {
                                  return Container(
                                    padding: EdgeInsets.only(top: 20),
                                    width: MediaQuery.of(context).size.width,
                                    child: InkWell(
                                      onTap: () => showDialog<String>(
                                        context: context,
                                        builder: (BuildContext context) =>
                                            AlertDialog(
                                          title: const Text(
                                              'Konfirmasi Pembayaran'),
                                          content: const Text(
                                              'Apakah Anda ingin melanjutkan pembayaran?'),
                                          actions: <Widget>[
                                            TextButton(
                                              onPressed: () => Navigator.pop(
                                                  context, 'Tidak'),
                                              child: const Text('Tidak'),
                                            ),
                                            TextButton(
                                              onPressed: () {
                                                if (snapshot
                                                        .data!.jumlahTagihan >
                                                    snapshotP.data!.saldo) {
                                                  showDialog<String>(
                                                      context: context,
                                                      builder: (BuildContext
                                                              context) =>
                                                          AlertDialog(
                                                            title: const Text(
                                                                'Saldo tidak cukup'),
                                                            content: const Text(
                                                                'Saldo Anda tidak cukup, silahkan melakukan top up terlebih dahulu'),
                                                            actions: <Widget>[
                                                              TextButton(
                                                                onPressed: () {
                                                                  Navigator
                                                                      .pushReplacement(
                                                                    context,
                                                                    MaterialPageRoute(
                                                                        builder:
                                                                            (context) =>
                                                                                HomePage(username: widget.username)),
                                                                  );
                                                                },
                                                                child: const Text(
                                                                    'Kembali'),
                                                              ),
                                                            ],
                                                          ));
                                                } else {
                                                  updateSaldo(
                                                      widget.username,
                                                      snapshot
                                                          .data!.jumlahTagihan);
                                                  updateTagihan(
                                                      widget.kode,
                                                      snapshot
                                                          .data!.tanggalTerbuat,
                                                      snapshot
                                                          .data!.tanggalTerbuat,
                                                      true,
                                                      snapshot
                                                          .data!.jumlahTagihan);
                                                  Navigator.pushReplacement(
                                                    context,
                                                    MaterialPageRoute(
                                                      builder: (context) =>
                                                          DetailTagihanPage(
                                                        username:
                                                            widget.username,
                                                        kode: widget.kode,
                                                      ),
                                                    ),
                                                  );
                                                }
                                              },
                                              child: const Text('Ya'),
                                            ),
                                          ],
                                        ),
                                      ),
                                      child: Container(
                                        height: 50,
                                        decoration: BoxDecoration(
                                          borderRadius:
                                              BorderRadius.circular(10),
                                          gradient: LinearGradient(
                                            colors: [
                                              Color.fromRGBO(143, 148, 251, 1),
                                              Color.fromRGBO(143, 148, 251, .6),
                                            ],
                                          ),
                                        ),
                                        child: Center(
                                          child: Text(
                                            "Bayar",
                                            style: TextStyle(
                                              color: Colors.white,
                                              fontWeight: FontWeight.bold,
                                            ),
                                          ),
                                        ),
                                      ),
                                    ),
                                  );
                                }
                                return Container();
                              }),
                            )
                          ],
                        );
                      } else if (snapshot.hasError) {
                        return Text('${snapshot.error}');
                      }
                      return const CircularProgressIndicator();
                    }),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

Future<void> updateSaldo(String username, int amount) async {
  http
      .put(
        Uri.parse('https://apap-104.cs.ui.ac.id/api/v1/pasien/' +
            username +
            '/bayarTagihan/' +
            amount.toString()),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
      )
      .then((response) => print(response.body))
      .catchError((error) => print(error));
}

Future<void> updateTagihan(String kode, String tanggalTerbuat,
    String tanggalBayar, bool isPaid, int jumlahTagihan) async {
  http
      .put(
        Uri.parse('https://apap-104.cs.ui.ac.id/api/v1/tagihan/' + kode),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(<String, dynamic>{
          "tanggalTerbuat": tanggalTerbuat,
          "tanggalBayar": tanggalTerbuat,
          "isPaid": isPaid,
          "jumlahTagihan": jumlahTagihan
        }),
      )
      .then((response) => print(response.body))
      .catchError((error) => print(error));
}
