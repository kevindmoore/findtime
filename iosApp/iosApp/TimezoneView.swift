/// Copyright (c) 2021 Razeware LLC
///
/// Permission is hereby granted, free of charge, to any person obtaining a copy
/// of this software and associated documentation files (the "Software"), to deal
/// in the Software without restriction, including without limitation the rights
/// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
/// copies of the Software, and to permit persons to whom the Software is
/// furnished to do so, subject to the following conditions:
///
/// The above copyright notice and this permission notice shall be included in
/// all copies or substantial portions of the Software.
///
/// Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
/// distribute, sublicense, create a derivative work, and/or sell copies of the
/// Software in any work that is designed, intended, or marketed for pedagogical or
/// instructional purposes related to programming, coding, application development,
/// or information technology.  Permission for such use, copying, modification,
/// merger, publication, distribution, sublicensing, creation of derivative works,
/// or sale is expressly withheld.
///
/// This project and source code may use libraries or frameworks that are
/// released under various Open-Source licenses. Use of those libraries and
/// frameworks are governed by their own individual licenses.
///
/// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
/// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
/// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
/// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
/// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
/// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
/// THE SOFTWARE.

import SwiftUI
import shared


struct TimezoneView: View {
  @EnvironmentObject var timezones: TimezoneItems
  @State private var timezoneHelper = TimeZoneHelperImpl()
  @State var currentDate = Date()
  let timer = Timer.publish(every: 1000, on: .main, in: .common).autoconnect()
  let timeFormatter: DateFormatter = {
    let formatter = DateFormatter()
    formatter.dateStyle = .none
    formatter.timeStyle = .short
    return formatter
  }()
  let dateFormatter: DateFormatter = {
    let formatter = DateFormatter()
    formatter.dateStyle = .medium
    formatter.timeStyle = .none
    return formatter
  }()
  @State var showTimezoneDialog = false

  var body: some View {
    VStack {
      NavigationView {
        VStack {
          Text("\(currentDate, formatter: timeFormatter)")
            .bold()
            .font(.system(size: 56.0))
            .onReceive(timer) { input in
              currentDate = input
            }
          Spacer()
          Text("\(currentDate, formatter: dateFormatter)")
            .bold()
            .font(.system(size: 56.0))
            .onReceive(timer) { input in
              currentDate = input
            }
          Spacer()
          List {
            ForEach(Array(timezones.selectedTimezones), id: \.self) {  timezone in
              VStack(alignment: .leading) {
                HStack {
                  Text(timezone)
                  Spacer()
                  Text(timezoneHelper.getTime(timezoneId: timezone))
                }
                .padding(20)
              }
              .frame(maxWidth: .infinity, minHeight: 80)
              .background(Color.white)
              .modifier(CardModifier())
            }
            .onDelete(perform: deleteItems)
          }
          .frame(maxWidth: .infinity)
          .background(Color.white)

          Spacer()
        } // VStack
        .toolbar {
          ToolbarItem(placement: .navigationBarTrailing) {
            HStack {
              Spacer()
              Image(systemName: "plus")
                .frame(alignment: .trailing)
            }
            .onTapGesture {
              showTimezoneDialog = true
            }
          }
        }
      } // NavigationView
      .frame(
        minWidth: 0,
        maxWidth: .infinity,
        minHeight: 0,
        maxHeight: .infinity,
        alignment: .top
      )
      Text("").hidden().sheet(isPresented: $showTimezoneDialog) {
        TimezoneDialog(showTimezoneDialog: $showTimezoneDialog)
          .environmentObject(timezones)
      }
    } // VStack
  }

  func deleteItems(at offsets: IndexSet) {
    for index in offsets {
      let element = Array(timezones.selectedTimezones)[index]
      timezones.selectedTimezones.remove(element)
    }
  }
}


struct TimezoneView_Previews: PreviewProvider {
  static var previews: some View {
    TimezoneView()
      .environmentObject(TimezoneItems())
  }
}
