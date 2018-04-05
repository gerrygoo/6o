//
//  Converter.swift
//  MiKmConverter
//
//  Created by Gerry G on 3/14/18.
//  Copyright © 2018 Gerry G. All rights reserved.
//

import Foundation

class Converter {
    var miles = 0.0
    var kilometers = 0.0
    
    func mileToKm() { kilometers = miles * 0.6 }
    
    func kmToMile() { miles = kilometers / 1.6 }
}
