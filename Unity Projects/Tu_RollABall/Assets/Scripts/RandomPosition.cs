using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RandomPosition : MonoBehaviour {

	// Use this for initialization
	void Start () {
        transform.position = new Vector3(Random.Range(-9.0f, 9.0f), 0.5f, Random.Range(-9.0f, 9.0f));
    }
}
