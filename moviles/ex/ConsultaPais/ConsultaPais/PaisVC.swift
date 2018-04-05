//
//  PaisVC.swift
//  ConsultaPais
//
//  Created by Gerry G on 4/4/18.
//  Copyright © 2018 Gerry G. All rights reserved.
//

import UIKit
import WebKit

class PaisVC: UIViewController {

    @IBOutlet weak var webPais: WKWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let dir = "http://en.wikipedia.org/wiki/Mexico"
        let url = URL(string: dir)!
        
        let req = URLRequest(url: url)
        
        webPais.load(req)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
