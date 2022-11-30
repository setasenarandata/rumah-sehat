import 'package:flutter/material.dart';

import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/profile/components/ProfileTopup.dart';
import 'package:rumahsehat_mobile/screens/profile/profile_page.dart';

class TopupPage extends StatefulWidget {
  const TopupPage({Key? key}) : super(key: key);

  @override
  State<TopupPage> createState() => _TopupPageState();
}

class _TopupPageState extends State<TopupPage> {
  var formatter = NumberFormat('###,###,###');
  int amount = 0;
  List<bool> isSelected = [true, false, false];
  List<int> quickAmountValues = [10000, 25000, 50000, 100000, 200000, 500000];

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: BackButton(
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: SingleChildScrollView(
        child: Container(
          child: Column(
            children: <Widget>[
              ProfileTopup(),
              Container(
                padding: EdgeInsets.only(
                    left: kDefaultPadding, right: kDefaultPadding),
                height: size.height * 0.5,
                child: Column(
                  children: <Widget>[
                    Row(
                      children: [
                        IconButton(
                          iconSize: 40,
                          onPressed: () {
                            setState(() {});
                            if (amount != 0) {
                              amount -= 5000;
                              print(amount);
                            }
                          },
                          icon: Icon(Icons.remove_circle_rounded),
                        ),
                        Spacer(),
                        Text(
                          "IDR",
                          style: TextStyle(
                              fontSize: 20, fontWeight: FontWeight.bold),
                        ),
                        const SizedBox(width: 10),
                        Text(
                          formatter.format(amount),
                          style: TextStyle(
                              fontSize: 40, fontWeight: FontWeight.bold),
                        ),
                        Spacer(),
                        IconButton(
                          iconSize: 40,
                          onPressed: () {
                            setState(() {});
                            amount += 5000;
                            print(amount);
                          },
                          icon: Icon(Icons.add_circle_rounded),
                        ),
                      ],
                    ),
                    const SizedBox(height: 40),
                    Slider.adaptive(
                      value: amount.toDouble(),
                      onChanged: (newAmount) {
                        setState(() => amount = newAmount.toInt());
                      },
                      min: 0,
                      max: 500000,
                      divisions: 20,
                      thumbColor: Colors.pink.shade200,
                      activeColor: Colors.purple.shade200,
                      inactiveColor: Colors.pink.shade100,
                    ),
                    const SizedBox(height: 60),
                    Row(
                      children: <Widget>[
                        QuickAmount(100000),
                        const Spacer(),
                        QuickAmount(250000),
                        const Spacer(),
                        QuickAmount(500000),
                      ],
                    ),
                    const SizedBox(
                      height: 80,
                    ),
                    SizedBox(
                      width: double.infinity,
                      child: TextButton(
                        onPressed: () async {
                          bool isSuccess = await patchSaldo(
                              "402892eb84c828d90184c84754550002", amount);

                          if (isSuccess) {
                            print("top up saldo sukses!!");
                            Navigator.push(context,
                                MaterialPageRoute(builder: (context) {
                              return ProfilePage();
                            }));
                          }
                        },
                        child: Text(
                          "Confirm Payment",
                          style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              color: amount == 0
                                  ? Colors.grey.shade300
                                  : Colors.white),
                        ),
                        style: ButtonStyle(
                          backgroundColor: amount == 0
                              ? MaterialStateProperty.all<Color>(Colors.white)
                              : MaterialStateProperty.all<Color>(
                                  Colors.blue.shade300),
                          foregroundColor:
                              MaterialStateProperty.all<Color>(Colors.blue),
                          padding: MaterialStateProperty.all<EdgeInsets>(
                            EdgeInsets.all(kDefaultPadding / 1.5),
                          ),
                          shape:
                              MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(16),
                              side: amount == 0
                                  ? BorderSide(color: Colors.white)
                                  : BorderSide(color: Colors.blue.shade300),
                            ),
                          ),
                        ),
                      ),
                    )
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  SizedBox QuickAmount(int value) {
    return SizedBox(
      width: 100,
      child: TextButton(
        onPressed: () {
          setState(() {});
          amount = value;
        },
        child: Text(
          formatter.format(value),
          style: TextStyle(
              fontSize: 16, fontWeight: FontWeight.bold, color: Colors.black),
        ),
        style: ButtonStyle(
          foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
          padding: MaterialStateProperty.all<EdgeInsets>(
            EdgeInsets.all(kDefaultPadding / 1.5),
          ),
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(16),
              side: BorderSide(color: Colors.blue.shade300),
            ),
          ),
        ),
      ),
    );
  }
}

Future<bool> patchSaldo(String id, int amount) async {
  final response = await http.patch(Uri.parse(
      'http://localhost:8080/api/v1/pasien/' +
          id +
          '/topup/' +
          amount.toString()));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return Future<bool>.value(true);
  } else {
    return Future<bool>.value(false);
  }
}
