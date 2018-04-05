//
//  ViewController.swift
//  MiKmConverter
//
//  Created by Gerry G on 3/14/18.
//  Copyright © 2018 Gerry G. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var milesTf: UITextField!
    
    @IBOutlet weak var kilometersTf: UITextField!
    
    
    @IBAction func convert( _ sender: Any) {
        let model = Converter()
        model.miles = Double(milesTf.text!)!
        model.kilometers = Double(kilometersTf.text!)!
        model.mileToKm()
        
        kilometersTf.text = "\(model.kilometers)"
        
        milesTf.resignFirstResponder()
        kilometersTf.resignFirstResponder()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

