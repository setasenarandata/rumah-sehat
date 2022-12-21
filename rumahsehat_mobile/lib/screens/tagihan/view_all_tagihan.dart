import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/screens/bills/detail_tagihan.dart';
import 'package:rumahsehat_mobile/screens/profile/components/RSCard.dart';
import 'package:rumahsehat_mobile/screens/tagihan/tagihan_model.dart';

class ViewAllTagihan extends StatefulWidget {
  final String username;
  const ViewAllTagihan({Key? key, required this.username}) : super(key: key);

  @override
  ViewAllTagihanState createState() => ViewAllTagihanState();
}

class ViewAllTagihanState extends State<ViewAllTagihan> {
  late Future<List<TagihanModel>> futureTagihan;

  @override
  void initState() {
    super.initState();
    futureTagihan = fetchMyBills(widget.username);
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        title: Text("View All Tagihan"),
        centerTitle: true,
      ),
      body: Column(
        children: [
          Expanded(
              child: Container(
            decoration: BoxDecoration(
              borderRadius: BorderRadius.only(),
              // color: kOtherColor
            ),
            child: FutureBuilder<List<TagihanModel>>(
              future: futureTagihan,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  List<TagihanModel>? listTagihan = snapshot.data;
                  return ListView.builder(
                      physics: BouncingScrollPhysics(),
                      itemCount: listTagihan?.length,
                      itemBuilder: (BuildContext context, int index) {
                        return Container(
                          child: Column(children: [
                            Container(
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.vertical(),
                                  color: Colors.white,
                                  boxShadow: [
                                    BoxShadow(
                                      // color: kTextLightColor,
                                      blurRadius: 2.0,
                                    ),
                                  ],
                                ),
                                child: RSCard(
                                  size: size,
                                  saldo: listTagihan![index].jumlahTagihan,
                                  colorAccent: Colors.blue,
                                  username: widget.username,
                                  rightTitle: "Bayar",
                                  details: listTagihan![index].kode,
                                  decoration: listTagihan![index].isPaid
                                      ? "LUNAS"
                                      : "BELUM DIBAYAR",
                                  fungsi: () {
                                    Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) =>
                                              DetailTagihanPage(
                                                username: widget.username,
                                                kode: listTagihan![index].kode,
                                              )),
                                    );
                                  },
                                )
                                // Column(
                                //   children: [
                                //     Row(
                                //       children: [
                                //         Text(listTagihan![index].kode,
                                //             style: Theme.of(context)
                                //                 .textTheme
                                //                 .caption),
                                //         Text(
                                //             listTagihan![index].isPaid.toString(),
                                //             style: Theme.of(context)
                                //                 .textTheme
                                //                 .caption),
                                //         Text(listTagihan![index].tanggalTerbuat,
                                //             style: Theme.of(context)
                                //                 .textTheme
                                //                 .caption),
                                //         Text(
                                //             listTagihan![index]
                                //                 .jumlahTagihan
                                //                 .toString(),
                                //             style: Theme.of(context)
                                //                 .textTheme
                                //                 .subtitle1),
                                //       ],
                                //     ),
                                //     SizedBox(
                                //       // height: kDefaultPadding,
                                //       child: Divider(
                                //         thickness: 1.0,
                                //       ),
                                //     ),
                                //   ],
                                // ),
                                ),
                          ]),
                        );
                      });
                } else if (snapshot.hasError) {
                  return Text("${snapshot.error}");
                }
                // By default show a loading spinner.
                return CircularProgressIndicator();
              },
            ),
          )),
        ],
      ),
    );
  }
}

Future<List<TagihanModel>> fetchMyBills(String username) async {
  final response = await http.get(Uri.parse(
      'https://apap-104.cs.ui.ac.id/api/v1/list-tagihan/' + username));

  if (response.statusCode == 200) {
    return parseTagihan(response.body);
  } else {
    throw Exception('Failed to load profile appointments');
  }
}

List<TagihanModel> parseTagihan(String responseBody) {
  final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();

  return parsed
      .map<TagihanModel>((json) => TagihanModel.fromJson(json))
      .toList();
}
