import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';

class Tagihan {
  final String kode;
  final String tanggalTerbuat;
  final bool isPaid;
  final int jumlahTagihan;

  const Tagihan(
      {required this.kode,
      required this.tanggalTerbuat,
      required this.isPaid,
      required this.jumlahTagihan});

  factory Tagihan.fromJson(Map<String, dynamic> json) {
    return Tagihan(
        kode: json['kode'],
        tanggalTerbuat: json['tanggalTerbuat'],
        isPaid: json['isPaid'],
        jumlahTagihan: json['jumlahTagihan']);
  }
}

Future<Tagihan> fetchDetailTagihan(String kode) async {
  final response = await http
      .get(Uri.parse('https://apap-104.cs.ui.ac.id/api/v1/tagihan/' + kode));

  if (response.statusCode == 200) {
    return Tagihan.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Failed to load bills');
  }
}
