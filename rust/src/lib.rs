use jni::objects::JObject;
use jni::sys::jdouble;
use jni::JNIEnv;

/// Returns the native engine version.
#[no_mangle]
pub extern "system" fn Java_com_trey_workstation_MainActivity_nativeEngineVersion(
    _env: JNIEnv,
    _this: JObject,
) -> jdouble {
    1.0
}

/// Calculates the distance between two 3D points.
///
/// Each point is represented by its x, y, and z coordinates.
#[no_mangle]
pub extern "system" fn Java_com_trey_workstation_MainActivity_nativeDistance3D(
    _env: JNIEnv,
    _this: JObject,
    x1: jdouble,
    y1: jdouble,
    z1: jdouble,
    x2: jdouble,
    y2: jdouble,
    z2: jdouble,
) -> jdouble {
    let dx = x2 - x1;
    let dy = y2 - y1;
    let dz = z2 - z1;

    (dx * dx + dy * dy + dz * dz).sqrt()
}

/// Calculates whether two axis-aligned bounding boxes overlap.
///
/// Each box is represented by its minimum and maximum coordinates.
#[no_mangle]
pub extern "system" fn Java_com_trey_workstation_MainActivity_nativeBoxesOverlap(
    _env: JNIEnv,
    _this: JObject,
    a_min_x: jdouble,
    a_min_y: jdouble,
    a_min_z: jdouble,
    a_max_x: jdouble,
    a_max_y: jdouble,
    a_max_z: jdouble,
    b_min_x: jdouble,
    b_min_y: jdouble,
    b_min_z: jdouble,
    b_max_x: jdouble,
    b_max_y: jdouble,
    b_max_z: jdouble,
) -> jni::sys::jboolean {
    let overlaps =
        a_min_x <= b_max_x
        && a_max_x >= b_min_x
        && a_min_y <= b_max_y
        && a_max_y >= b_min_y
        && a_min_z <= b_max_z
        && a_max_z >= b_min_z;

    if overlaps {
        1
    } else {
        0
    }
}
