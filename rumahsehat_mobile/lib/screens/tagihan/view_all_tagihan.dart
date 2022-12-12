import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/screens/tagihan/tagihan_model.dart';

class ViewAllTagihan extends StatefulWidget {
  const ViewAllTagihan({
    Key? key,
  }) : super(key: key);

  @override
  ViewAllTagihanState createState() => ViewAllTagihanState();
}

class ViewAllTagihanState extends State<ViewAllTagihan> {
  final String url = "https://fda16979-b27e-4d69-b4a2-4bb071feb7ec.mock.pstmn.io/api/v1/tagihan/123";
  late Future<List<TagihanModel>> futureTagihan;

  Future<List<TagihanModel>> getAllTagihan() async {
    var res = await http.get(Uri.parse(url),
        headers: <String, String>{
          'Content-Type' : 'application/json;charset=UTF-8',
        });
    if (res.statusCode == 200) {
      List jsonResponse = json.decode(res.body);
      print(jsonResponse);
      return jsonResponse.map((data) => TagihanModel.fromJson(data)).toList();
    }
    else {
      throw Exception('Appointment gagal ditampilkan');
    }
  }

  @override
  void initState() {
    super.initState();
    futureTagihan = getAllTagihan();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("View All Tagihan"),
        centerTitle: true,
      ),
      body: Column(
        children: [
          Expanded(child: Container(
            decoration: BoxDecoration(
                borderRadius: BorderRadius.only(
                ),
                // color: kOtherColor
            ),
            child: FutureBuilder<List<TagihanModel>> (
              future: futureTagihan,
              builder: (context, snapshot){
                if(snapshot.hasData) {
                  List<TagihanModel>? listTagihan = snapshot.data;
                  return ListView.builder(
                      physics: BouncingScrollPhysics(),
                      itemCount: listTagihan?.length,
                      itemBuilder: (BuildContext context, int index) {
                        return
                          Card(
                            child: Padding(
                              padding: EdgeInsets.all(12.0),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Text(
                                    snapshot.data![index].kode,
                                    style: TextStyle(
                                      fontSize: 18,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                  SizedBox(height: 7),
                                  Text("Kode : " + listTagihan![index].kode, style: Theme.of(context).textTheme.caption),
                                  Text("Status : " + listTagihan![index].isPaid.toString(), style: Theme.of(context).textTheme.caption),
                                  Text("Tanggal Terbuat : " + listTagihan![index].tanggalTerbuat, style: Theme.of(context).textTheme.caption),
                                  Text("Jumlah Tagihan : " + listTagihan![index].jumlahTagihan.toString(), style: Theme.of(context).textTheme.subtitle1),
                                  SizedBox(
                                    height: 12,
                                  ),
                                ],
                              ),
                            ),
                          );

                        // Container(
                          //   child: Column(
                          //       children: [
                          //         Container(
                          //           decoration: BoxDecoration(
                          //             borderRadius: BorderRadius.vertical(
                          //             ),
                          //             color: Colors.white,
                          //             boxShadow: [
                          //               BoxShadow(
                          //                 // color: kTextLightColor,
                          //                 blurRadius: 2.0,
                          //               ),
                          //             ],
                          //           ),
                          //           child: Column(
                          //             children: [
                          //               Row(
                          //                 children: [
                          //                   Text(listTagihan![index].kode, style: Theme.of(context).textTheme.caption),
                          //                   Text(listTagihan![index].isPaid.toString(), style: Theme.of(context).textTheme.caption),
                          //                   Text(listTagihan![index].tanggalTerbuat, style: Theme.of(context).textTheme.caption),
                          //                   Text(listTagihan![index].jumlahTagihan.toString(), style: Theme.of(context).textTheme.subtitle1),
                          //                 ],
                          //               ),
                          //               SizedBox(
                          //                 // height: kDefaultPadding,
                          //                 child: Divider(
                          //                   thickness: 1.0,
                          //                 ),
                          //               ),
                          //             ],
                          //           ),
                          //         ),
                          //       ]),
                          // );
                      }
                  );

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